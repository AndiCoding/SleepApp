package org.sleepapp.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.data.repository.AlarmScheduler


class AlarmViewModel(
private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {
    private val _now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun getNow(): LocalDateTime {
        return _now
    }

    private val _currentAlarm = MutableStateFlow(_now)
    val currentAlarm: StateFlow<LocalDateTime> get() = _currentAlarm
    fun setCurrentAlarm(time: LocalDateTime){
       _currentAlarm.value = time
    }

    private val _alarms: StateFlow<List<Alarm>> = alarmRepository
        .alarmsFlow

    val alarms: StateFlow<List<Alarm>> get() = _alarms

       fun insertAlarm(): Long {
           val id = mutableLongStateOf(0)
        val alarm = Alarm(
            startAlarm = _now,
            endAlarm = _currentAlarm.value
        )
        viewModelScope.launch {
            id.value = alarmRepository.insertAlarm(alarm)
            alarmScheduler.scheduleAlarm(alarm)
        }
           return id.value
    }

    fun updateAlarm(alarmItem: Alarm) {
        viewModelScope.launch {
            alarmRepository.updateAlarm(alarmItem)
        }
    }

    fun deleteAlarm(alarmItem: Alarm) {
        viewModelScope.launch {
            alarmRepository.deleteAlarm(alarmItem)
        }
    }

    fun getAlarmById(id: Long): Alarm? {
        val resultFlow = MutableStateFlow<Alarm?>(null)
        viewModelScope.launch {
            resultFlow.value = alarmRepository.getAlarmById(id)
        }
        return resultFlow.value
    }

    fun createAlarmWithSameWakeupTime(wakeupTime: LocalDateTime): Alarm? {
        val resultFlow = MutableStateFlow<Alarm?>(null)
        val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val wakeupTime = LocalTime(wakeupTime.hour, wakeupTime.minute)
        var newWakeupDate = LocalDateTime(
            currentDateTime.date,
            wakeupTime
        )
        if (newWakeupDate <= currentDateTime) {
            val tomorrow = currentDateTime.date.plus(1, DateTimeUnit.DAY)
            newWakeupDate = LocalDateTime(tomorrow, wakeupTime)
        }

        val newAlarm = Alarm(
            startAlarm = currentDateTime,
            endAlarm = newWakeupDate
        )
        var insertedAlarmId = 0L

        viewModelScope.launch {
            insertedAlarmId = alarmRepository.insertAlarm(newAlarm)
            alarmScheduler.scheduleAlarm(newAlarm)
            if (insertedAlarmId != 0L) {
                resultFlow.value = alarmRepository.getAlarmById(insertedAlarmId)
            }
        }
        return resultFlow.value
    }



}