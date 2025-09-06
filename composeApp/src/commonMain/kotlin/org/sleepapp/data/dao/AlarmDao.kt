package org.sleepapp.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import cache.AlarmDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.util.localTimeToString
import org.sleepapp.data.util.stringToLocalTime


class AlarmDao(private val queries: AlarmDatabaseQueries) {
    fun getAlarmsFlow(): Flow<List<Alarm>> = queries
        .getAllAlarms()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { cacheAlarms ->
            cacheAlarms.map { cacheAlarm ->
                Alarm(
                    id = cacheAlarm.id,
                    startAlarm = stringToLocalTime( cacheAlarm.start_time),
                    endAlarm = stringToLocalTime(cacheAlarm.end_time),
                    interval = cacheAlarm.interval
                )
            }
        }
        .flowOn(Dispatchers.IO)

    fun insert(alarm: Alarm){
        val startAlarm = localTimeToString(alarm.startAlarm)
        val endAlarm = localTimeToString(alarm.endAlarm)
        queries.insertAlarm(startAlarm, endAlarm, alarm.interval)
    }

    fun update(alarm: Alarm) {
        val startAlarm = localTimeToString(alarm.startAlarm)
        val endAlarm = localTimeToString(alarm.endAlarm)
        queries.updateAlarm(startAlarm, endAlarm, alarm.interval, alarm.id)
    }

    fun delete(alarm: Alarm) {
        queries.deleteAlarm(alarm.id)
    }

}