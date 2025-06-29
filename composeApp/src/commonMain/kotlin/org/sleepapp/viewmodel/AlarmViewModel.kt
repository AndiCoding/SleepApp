package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cache.Alarm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.sleepapp.data.model.AlarmItem
import org.sleepapp.data.repository.AlarmRepository


class AlarmViewModel(
//private val alarmRepository: AlarmRepository
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
/*
    fun insertAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch { alarmRepository.insertAlarm(alarmItem) }
    }

    fun updateAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch { alarmRepository.updateAlarm(alarmItem) }
    }

    fun deleteAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch { alarmRepository.deleteAlarm(alarmItem) }
    }

    fun getAllAlarm() : List<Alarm> {
        return alarmRepository.getAllAlarms()
    }


 */
}