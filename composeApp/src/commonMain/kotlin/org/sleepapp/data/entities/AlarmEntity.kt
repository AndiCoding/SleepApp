package org.sleepapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val startAlarm: LocalDateTime,
    val endAlarm: LocalDateTime,
    val interval: Long = 5000
)