package org.sleepapp.data.repository

import cache.Database
import cache.DatabaseDriverFactory
import org.sleepapp.data.model.Alarm

class AlarmRepository(
    private val databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = Database(databaseDriverFactory)


    fun insertAlarm(alarmItem: Alarm) {
            database.addAlarm(
                alarmItem.startHour,
                alarmItem.startMinute,
                alarmItem.endHour,
                alarmItem.endMinute
            )
    }

    fun updateAlarm(alarmItem: Alarm) {
        database.updateAlarm(alarmItem.id,
            alarmItem.startHour,
            alarmItem.startMinute,
            alarmItem.endHour,
            alarmItem.endMinute
        )
    }

    fun deleteAlarm(alarmItem: Alarm) {
        database.deleteAlarm(alarmItem.id)
    }

    fun getAllAlarms() : List<Alarm> {
        return database.getAllAlarms()
    }

}