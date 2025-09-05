package org.sleepapp.data.repository

import cache.Database
import cache.DatabaseDriverFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sleepapp.data.model.Alarm

class AlarmRepository(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = Database(databaseDriverFactory)


    fun insertAlarm(alarmItem: Alarm) {
            database.addAlarm(
                alarmItem.startAlarm,
                alarmItem.endAlarm,
                alarmItem.interval
            )
    }

    fun updateAlarm(alarmItem: Alarm) {
        database.updateAlarm(alarmItem.id,
            alarmItem.startAlarm,
            alarmItem.endAlarm,
            alarmItem.interval
        )
    }

    fun deleteAlarm(alarmItem: Alarm) {
        database.deleteAlarm(alarmItem.id)
    }


      fun getAllAlarmsFlow(): StateFlow<List<Alarm>> {
        return database.alarmsFlow
    }
}