package org.sleepapp.data.repository

import org.sleepapp.data.dao.AlarmDao
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.util.createRandomDateTime

class AlarmRepository(private val alarmDao: AlarmDao) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val alarmsFlow: StateFlow<List<Alarm>> = alarmDao
        .getAlarmsFlow()
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    suspend fun insertAlarm(alarm: Alarm): Long {


        return alarmDao.insert(alarm)
    }

    suspend fun updateAlarm(alarm: Alarm) {
        alarmDao.update(alarm)
    }

    suspend fun deleteAlarm(alarm: Alarm) {
        alarmDao.delete(alarm)
    }

    fun getAlarmById(id: Long): Flow<Alarm?> {
        return alarmDao.getAlarmById(id)
    }
}