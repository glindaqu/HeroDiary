package com.example.herodiary.notify

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import java.util.Calendar
import java.util.Date
import android.provider.Settings
import androidx.annotation.RequiresApi

fun scheduleNotification(context: Context, title: String, message: String, date: Date)  {
    val intent = Intent(context, Notification::class.java)
    intent.putExtra(titleExtra, title)
    intent.putExtra(messageExtra, message)

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        notificationID,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val time = getTime(date)
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        time,
        pendingIntent
    )
    showAlert(time, title, message, context)
}

private fun showAlert(time: Long, title: String, message: String, context: Context)  {
    val date = Date(time)
    val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
    val timeFormat = android.text.format.DateFormat.getTimeFormat(context)

    AlertDialog.Builder(context)
        .setTitle("Notification Scheduled")
        .setMessage(
            "Title: " + title +
                    "\nMessage: " + message +
                    "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
        .setPositiveButton("Okay"){_,_ ->}
        .show()
}

private fun getTime(date: Date): Long  {
    val calendar = Calendar.getInstance()
    date.hours = 9
    date.minutes = 1
    calendar.time = date
    return calendar.timeInMillis
}

fun createNotificationChannel(context: Context)  {
    val name = "Notif Channel"
    val desc = "A Description of the Channel"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(channelID, name, importance)
    channel.description = desc
    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("ServiceCast")
fun checkNotificationPermissions(context: Context): Boolean {
    val notificationManager =
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
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
