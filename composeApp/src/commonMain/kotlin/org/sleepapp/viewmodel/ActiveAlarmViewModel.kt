package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository

class ActiveAlarmViewModel(private val alarmRepository: AlarmRepository ) : ViewModel() {

    private val _activeAlarm = MutableStateFlow<Alarm?>(null)
    val activeAlarm get() = _activeAlarm


    fun getAlarmById(alarmId: Long){
        viewModelScope.launch {
            alarmRepository.getAlarmById(alarmId)?.let { alarm ->
                _activeAlarm.value = alarm
            }
        }

    }

}