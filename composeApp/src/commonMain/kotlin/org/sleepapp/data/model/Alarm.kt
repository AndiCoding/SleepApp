package org.sleepapp.data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Alarm(
    val id:Long = 0,
    val startAlarm: LocalDateTime,
    val endAlarm: LocalDateTime,
    val interval: Long = 5000,
)