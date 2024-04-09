package com.example.herodiary.shared

import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.concurrent.TimeUnit

fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())

fun getDateDiff(date: Long): Long {
    return TimeUnit.MILLISECONDS.toDays(Date().time - Date(date).time)
}

fun getDateDiff(d1: Long, d2: Long): Long {
    return TimeUnit.MILLISECONDS.toDays(Date(d1).time - Date(d2).time)
}