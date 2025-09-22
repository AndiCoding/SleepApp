package org.sleepapp.data.model

import kotlinx.datetime.LocalDateTime

data class Alarm(
    val id:Long = 0,
    val startAlarm: LocalDateTime,
    val endAlarm: LocalDateTime,
    val interval: Long = 5000,
)