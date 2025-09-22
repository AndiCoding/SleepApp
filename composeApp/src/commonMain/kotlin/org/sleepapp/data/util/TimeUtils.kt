package org.sleepapp.data.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

val DEFAULT_LOCAL_DATE_TIME = LocalDateTime(1970, 1, 1, 0, 0)


fun getNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}
fun localDateTimetoHourAndMinute(time: LocalDateTime): String {
    return "${time.hour.toString().padStart(2, '0')}:${time.minute.toString().padStart(2, '0')}"
}

fun calculateNextWakeupDateTime(wakeupTime: LocalTime): LocalDateTime{
    val currentDateTime = getNow()
    var nextWakeupDateTime = LocalDateTime(currentDateTime.date, wakeupTime)
    if (nextWakeupDateTime <= currentDateTime) {
        val tomorrow = currentDateTime.date.plus(1, DateTimeUnit.DAY)
        nextWakeupDateTime = LocalDateTime(tomorrow, wakeupTime)
    }

    return nextWakeupDateTime
}

