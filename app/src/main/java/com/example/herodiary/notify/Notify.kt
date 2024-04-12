package com.example.herodiary.notify

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.herodiary.database.room.models.TaskRoomModel
import java.util.Calendar
import java.util.Date
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import java.text.SimpleDateFormat


@SuppressLint("ScheduleExactAlarm")
fun scheduleNotification(context: Context, taskRoomModel: TaskRoomModel) {
    val intent = Intent(context, Notification::class.java)

    val title = "Whoa! There is a deadline! Hurry up!"
    val message = "The task named ${taskRoomModel.title} is over today"

    intent.putExtra(titleExtra, title)
    intent.putExtra(messageExtra, message)

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        notificationID,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val date = Date(taskRoomModel.deadline!!)
    date.hours = 8
    date.minutes = 0
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        date.time,
        pendingIntent
    )

    showAlert(date.time, title, message, context)
}

@SuppressLint("SimpleDateFormat")
fun showAlert(time: Long, title: String, message: String, context: Context) {
    val date = Date(time)

    AlertDialog.Builder(context)
        .setTitle("Notification Scheduled")
        .setMessage(
            "Title: $title\nMessage: $message\nAt: ${SimpleDateFormat("MMMM, dd, yyyy").format(date)} ${SimpleDateFormat("m, hh").format(date)}"
        )
        .setPositiveButton("Okay") { _, _ -> }
        .show()
}

fun getTime(date: Date): Long {
    val minute = 0
    val hour = 8

    val calendar = Calendar.getInstance()
    calendar.set(date.year, date.month, date.day, hour, minute)

    return calendar.timeInMillis
}

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("ServiceCast")
fun checkNotificationPermissions(context: Context): Boolean {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val isNotifyEnabled = notificationManager.areNotificationsEnabled()
    val isAlarmEnabled = alarmManager.canScheduleExactAlarms()

    if (!isNotifyEnabled) {
        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        context.startActivity(intent)

        return false
    }

    if (!isAlarmEnabled) {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        context.startActivity(intent)

        return false
    }

    return true
}
