package org.sleepapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import org.sleepapp.data.database.RoomDb
import org.sleepapp.data.model.AlarmItem

class AlarmRepository(
    private val database: RoomDb
) {
    private val dispatcher = Dispatchers.IO

    suspend fun insertAlarm(alarmItem: AlarmItem) {
        with(dispatcher){
            database.alarmDao().insert(alarmItem)
        }
    }

    suspend fun updateAlarm(alarmItem: AlarmItem) {
        with(dispatcher){
            database.alarmDao().update(alarmItem)
        }
    }

    suspend fun deleteAlarm(alarmItem: AlarmItem) {
        with(dispatcher){
            database.alarmDao().delete(alarmItem)
        }
    }

    fun getAllAlarms() : Flow<List<AlarmItem>?> {
        return database.alarmDao().getAll()
    }

}