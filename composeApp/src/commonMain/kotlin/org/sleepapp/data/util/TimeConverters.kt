package org.sleepapp.data.util

import kotlinx.datetime.LocalTime

fun localTimeToString(time: LocalTime): String = time.toString()

fun stringToLocalTime(value: String): LocalTime = LocalTime.parse(value)