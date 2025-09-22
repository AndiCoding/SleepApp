package org.sleepapp.data.repository

import org.sleepapp.data.dao.AlarmDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import org.sleepapp.data.entities.AlarmEntity
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.util.DEFAULT_LOCAL_DATE_TIME
import org.sleepapp.data.util.LocalDateTimeConverter

class AlarmRepository(private val alarmDao: AlarmDao) {

    fun getAllAlarmsFlow(): Flow<List<Alarm?>>{
        return alarmDao.getAllAsFlow().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun insertAlarm(alarm: Alarm): Long? {
        try {
            return alarm.toEntity()?.let {
                alarmDao.insert(it)
            }
        } catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }

    suspend fun updateAlarm(alarm: Alarm): Int? {
        try {
            return alarm.toEntity()?.let {
                alarmDao.update(it)
            }
        } catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }

    suspend fun deleteAlarm(alarm: Alarm): Int? {
        try {
            return alarm.toEntity()?.let {
                alarmDao.delete(it)
            }
        } catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }

    suspend fun getAlarmById(id: Long): Alarm? {
        try {
            val entity = alarmDao.getById(id)
            return entity?.toDomain()
        } catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }



    private fun AlarmEntity.toDomain(): Alarm? {
        return Alarm(
            id = this.id,
            startAlarm = LocalDateTimeConverter
                .fromString(this.startAlarm) ?: DEFAULT_LOCAL_DATE_TIME,
            endAlarm = LocalDateTimeConverter
                .fromString(this.endAlarm) ?: DEFAULT_LOCAL_DATE_TIME,
            interval = this.interval
        )

    }

    private fun Alarm.toEntity(): AlarmEntity? {
        return AlarmEntity(
            id = this.id,
            startAlarm = LocalDateTimeConverter
                .localDateTimeToString(this.startAlarm) ?: "",
            endAlarm = LocalDateTimeConverter
                .localDateTimeToString(this.endAlarm) ?: "",
            interval = this.interval
        )

    }

}