package org.sleepapp.data.repository

import cache.Alarm
import cache.Database
import cache.DatabaseDriverFactory
import org.sleepapp.data.model.AlarmItem

class AlarmRepository(
    private val databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = Database(databaseDriverFactory)


    fun insertAlarm(alarmItem: AlarmItem) {
            database.addAlarm(
                alarmItem.startHour,
                alarmItem.startMinute,
                alarmItem.endHour,
                alarmItem.endMinute
            )
    }

    fun updateAlarm(alarmItem: AlarmItem) {
        database.updateAlarm(alarmItem.id,
            alarmItem.startHour,
            alarmItem.startMinute,
            alarmItem.endHour,
            alarmItem.endMinute
        )
    }

    fun deleteAlarm(alarmItem: AlarmItem) {
        database.deleteAlarm(alarmItem.id)
    }

    fun getAllAlarms() : List<Alarm> {
        return database.getAllAlarms()
    }

}