package org.sleepapp.data.util

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

object LocalDateTimeConverter {
    @TypeConverter
    fun fromString(value: String?): LocalDateTime? =
        value?.let{ LocalDateTime.parse(it)}

    @TypeConverter
    fun localDateTimeToString(date: LocalDateTime?): String? =
        date?.toString()
}