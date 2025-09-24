package org.sleepapp.data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Alarm(
    val id:Long = 0,
    val startAlarm: LocalDateTime,
    val endAlarm: LocalDateTime,
    val interval: Long = 5000,
)