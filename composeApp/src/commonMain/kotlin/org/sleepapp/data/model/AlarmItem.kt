package org.sleepapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AlarmItem(
    @SerialName("id")
    val id:Long = 0,
    @SerialName("start_hour")
    val startHour: Long,
    @SerialName("start_minute")
    val startMinute: Long,
    @SerialName("end_hour")
    val endHour: Long,
    @SerialName("end_minute")
    val endMinute: Long,
    @SerialName("interval")
    val interval: String,
)