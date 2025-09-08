package org.sleepapp.data.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun localDateTimeToString(time: LocalDateTime): String = time.toString()

fun stringToLocalDateTime(value: String): LocalDateTime {
    return try {
        LocalDateTime.parse(value)
    } catch (e: Exception) {
        try {
            val localTime = LocalTime.parse(value)
            val today = LocalDate(1970, 1, 1)
            LocalDateTime(date = today, time = localTime)
        } catch (e: Exception) {
            LocalDateTime(1970, 1, 1, 0, 0)
        }
    }
}
fun getNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}
fun localDateTimetoHourAndMinute(time: LocalDateTime): String {
    return "${time.hour.toString().padStart(2, '0')}:${time.minute.toString().padStart(2, '0')}"
}