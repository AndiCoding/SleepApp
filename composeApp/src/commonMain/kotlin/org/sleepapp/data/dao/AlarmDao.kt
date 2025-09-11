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
import org.sleepapp.data.util.createRandomDateTime
import org.sleepapp.data.util.localDateTimeToString
import org.sleepapp.data.util.stringToLocalDateTime


class AlarmDao(private val queries: AlarmDatabaseQueries) {
    fun getAlarmsFlow(): Flow<List<Alarm>> = queries
        .getAllAlarms()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { cacheAlarms ->
            cacheAlarms.map { cacheAlarm ->
                Alarm(
                    id = cacheAlarm.id,
                    startAlarm = stringToLocalDateTime( cacheAlarm.start_time),
                    endAlarm = stringToLocalDateTime(cacheAlarm.end_time),
                    interval = cacheAlarm.interval
                )
            }
        }
        .flowOn(Dispatchers.IO)

    fun insert(alarm: Alarm): Long{
        val startAlarm = localDateTimeToString(alarm.startAlarm)
        val endAlarm = localDateTimeToString(alarm.endAlarm)
        queries.insertAlarm(startAlarm, endAlarm, alarm.interval)
        return queries.lastInsertedRowId().executeAsOne()
    }

    fun update(alarm: Alarm) {
        val startAlarm = localDateTimeToString(alarm.startAlarm)
        val endAlarm = localDateTimeToString(alarm.endAlarm)
        queries.updateAlarm(startAlarm, endAlarm, alarm.interval, alarm.id)
    }

    fun delete(alarm: Alarm) {
        queries.deleteAlarm(alarm.id)
    }


    suspend fun getAlarmById(id: Long): Alarm {
        return queries.getAlarmById(id)
            .executeAsOne()
            .let { cacheAlarm ->
                Alarm(
                    id = cacheAlarm.id,
                    startAlarm = stringToLocalDateTime(cacheAlarm.start_time),
                    endAlarm = stringToLocalDateTime(cacheAlarm.end_time),
                    interval = cacheAlarm.interval
                )
            }

    }

    /*
    fun getAlarmById(id: Long): Flow<Alarm?> {
        return queries.getAlarmById(id)
            .asFlow()
            .map { it.executeAsOneOrNull() }
            .map { alarmById ->
                alarmById?.let {
                    Alarm(
                        id = alarmById.id,
                        startAlarm = stringToLocalDateTime(alarmById.start_time),
                        endAlarm = stringToLocalDateTime(alarmById.end_time),
                        interval = alarmById.interval
                    )
                }
            }.flowOn(Dispatchers.IO)
            }

     */

}