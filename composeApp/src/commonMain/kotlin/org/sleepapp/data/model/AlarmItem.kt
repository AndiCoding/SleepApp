package org.sleepapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlarmItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val start: String,
    val end: String,
    val interval: String,
    val isOptionRevealed: Boolean
)
