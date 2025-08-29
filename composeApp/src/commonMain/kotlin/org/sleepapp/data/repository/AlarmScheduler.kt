package org.sleepapp.data.repository

import org.sleepapp.data.model.Alarm

interface AlarmScheduler {
    fun scheduleAlarm(alarm: Alarm)
    fun cancelAlarm(alarm: Alarm)
}