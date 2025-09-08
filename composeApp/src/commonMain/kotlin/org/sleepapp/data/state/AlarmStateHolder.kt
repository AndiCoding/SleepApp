package org.sleepapp.data.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository

class AlarmStateHolder(
    private val alarmRepository: AlarmRepository
)
 {
     private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
     private val _alarms = alarmRepository.alarmsFlow
    val alarms get() = _alarms

     private val _createdAlarm = MutableStateFlow<Alarm?>(null)
     val createdAlarm: StateFlow<Alarm?> = _createdAlarm

     suspend fun setCreatedAlarm(id: Long){
         alarmRepository.getAlarmById(id).collect { alarm ->
             _createdAlarm.value = alarm
         }
     }

     fun clearCreatedAlarm() {
         _createdAlarm.value = null
     }

     fun clear() {
         coroutineScope.cancel()
     }

}