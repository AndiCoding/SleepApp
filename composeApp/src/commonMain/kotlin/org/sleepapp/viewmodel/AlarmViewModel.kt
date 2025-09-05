package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.data.repository.AlarmScheduler


class AlarmViewModel(
private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {
    private val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time

    fun getCurrentTime(): String{
        return "${now.hour}:${now.minute}"
    }

    private val _currentAlarm = MutableStateFlow(now)
    val currentAlarm: StateFlow<LocalTime> get() = _currentAlarm
    fun setCurrentAlarm(time: LocalTime){
       _currentAlarm.value = time
    }

    private val _alarms: StateFlow<List<Alarm>> = alarmRepository
        .getAllAlarmsFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val alarms: StateFlow<List<Alarm>> get() = _alarms

       fun insertAlarm() {
        val alarm = Alarm(
            startAlarm = now,
            endAlarm = _currentAlarm.value
        )
        viewModelScope.launch {
            alarmRepository.insertAlarm(alarm)
            alarmScheduler.scheduleAlarm(alarm)
        }
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




}