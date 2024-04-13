package com.example.herodiary.notify

import java.util.Calendar
import java.util.Date

fun getTime(date: Date): Long  {
    val calendar = Calendar.getInstance()
    date.hours = 9
    date.minutes = 1
    calendar.time = date
    return calendar.timeInMillis
}