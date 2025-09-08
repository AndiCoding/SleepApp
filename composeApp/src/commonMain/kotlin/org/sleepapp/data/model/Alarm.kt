package org.sleepapp.data.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Alarm(
    @SerialName("id")
    val id:Long = 0,
    @SerialName("start_time")
    val startAlarm: LocalDateTime,
    @SerialName("end_time")
    val endAlarm: LocalDateTime,
    @SerialName("interval")
    val interval: Long = 5000,
)