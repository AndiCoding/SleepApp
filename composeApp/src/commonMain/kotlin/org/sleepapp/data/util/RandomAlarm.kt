package org.sleepapp.data.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.sleepapp.data.model.Alarm

fun randomAlarm(): Alarm {
    val newRandomDate: LocalDateTime = LocalDateTime(
        date = LocalDate(
            year = 1970,
            monthNumber = (1..12).random(),
            dayOfMonth = (1..28).random()
        ),
        time = LocalTime(
            hour = 0,
            minute = 0,
            second = 0,
            nanosecond = 0,
        )
    )
    return Alarm(
        startAlarm = newRandomDate,
        endAlarm = calculateNextWakeupDateTime(newRandomDate.time),
    )
}