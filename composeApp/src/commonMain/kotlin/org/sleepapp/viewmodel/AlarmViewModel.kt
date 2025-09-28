package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.data.repository.AlarmScheduler
import org.sleepapp.data.util.calculateNextWakeupDateTime
import org.sleepapp.data.util.getNow

class AlarmViewModel(
private val alarmRepository: AlarmRepository,
    //private val alarmScheduler: AlarmScheduler,
) : ViewModel() {
    private val _alarms = MutableStateFlow<List<Alarm?>>(emptyList())
    val alarms: StateFlow<List<Alarm?>> get() = _alarms

    private val _currentAlarm = MutableStateFlow(createEmptyAlarm())
    val currentAlarm: StateFlow<Alarm> = _currentAlarm

    private val _latestInsertedAlarmId = MutableStateFlow<Long?>(null)
    val latestInsertedAlarmId: StateFlow<Long?> get() = _latestInsertedAlarmId

    private val _selectedTime = MutableStateFlow<LocalTime>(getNow().time)
    val selectedTime = _selectedTime
    fun updateSelectedTime(localTime: LocalTime) {
        _selectedTime.value = localTime
    }


    init {
        viewModelScope.launch {
            alarmRepository.getAllAlarmsFlow()
                .catch{e -> e.printStackTrace()}
                .collect { alarmsList ->
                _alarms.value = alarmsList
            }
        }
    }

    fun createEmptyAlarm(): Alarm = Alarm(
            startAlarm = getNow(),
            endAlarm = getNow(),
        )

    fun clearCreatedAlarm() {
        _currentAlarm.value = createEmptyAlarm()
    }

    fun setCurrentAlarmEndtime(time: LocalDateTime){
        _currentAlarm.value = _currentAlarm.value.copy(endAlarm = time)
    }

    fun updateAlarm(alarmId: Long) {
        viewModelScope.launch {
            val alarm = alarmRepository.getAlarmById(alarmId)
            alarm?.let { alarm ->
                val nextDate =
                    calculateNextWakeupDateTime(
                        alarm.endAlarm.time
                    )
                val updatedDate = Alarm(
                    startAlarm = getNow(),
                    endAlarm = nextDate
                )
                _currentAlarm.value  = updatedDate
                alarmRepository.updateAlarm(_currentAlarm.value)
            }
        }
    }

    fun deleteAlarm(alarmItem: Alarm) {
        viewModelScope.launch {
            alarmRepository.deleteAlarm(alarmItem)
            clearCreatedAlarm()
        }
    }



    fun insertAlarm() {
        viewModelScope.launch {
            val insertedAlarmId = alarmRepository.insertAlarm(_currentAlarm.value)
            _latestInsertedAlarmId.value = insertedAlarmId
            insertedAlarmId?.let { id ->
                alarmRepository.getAlarmById(id)?.let { alarm ->
                    _currentAlarm.value = alarm
                   // alarmScheduler.scheduleAlarm(alarm)
                }
            }
        }
    }




}