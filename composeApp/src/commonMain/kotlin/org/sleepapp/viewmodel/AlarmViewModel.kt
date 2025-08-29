package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.data.repository.AlarmScheduler


class AlarmViewModel(
private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {
    private val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    fun getCurrentTime(): String{
        return "${now.hour}:${now.minute}"
    }

    private val _currentAlarm = MutableStateFlow(now.hour to now.minute)
    val currentAlarm: StateFlow<Pair<Int,Int>> get() = _currentAlarm
    fun setCurrentAlarm(hour: Int, minute: Int){
       _currentAlarm.value = hour to minute
    }

    fun insertAlarm() {
        val alarm = Alarm(
            startHour = now.hour.toLong(),
            startMinute = now.minute.toLong(),
            endHour = _currentAlarm.value.first.toLong(),
            endMinute = _currentAlarm.value.second.toLong(),
            interval = "NOT IMPLEMENTED"
        )
        viewModelScope.launch {
            alarmRepository.insertAlarm(alarm)
            alarmScheduler.scheduleAlarm(alarm)

        }
    }

    fun updateAlarm(alarmItem: Alarm) {
        viewModelScope.launch { alarmRepository.updateAlarm(alarmItem) }
    }

    fun deleteAlarm(alarmItem: Alarm) {
        viewModelScope.launch { alarmRepository.deleteAlarm(alarmItem) }
    }

    fun getAllAlarm() : List<Alarm> {
        return alarmRepository.getAllAlarms()
    }



}