package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.plus
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.data.repository.AlarmScheduler
import org.sleepapp.data.util.calculateNextWakeupDateTime
import org.sleepapp.data.util.getNow
import org.sleepapp.data.util.randomAlarm


class AlarmViewModel(
private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler,
) : ViewModel() {


    private val _alarms = alarmRepository.alarmsFlow
    val alarms get() = _alarms

    private val _createdAlarm = MutableStateFlow(createEmptyAlarm())

    fun createEmptyAlarm(): Alarm = Alarm(
            startAlarm = getNow(),
            endAlarm = getNow(),
        )

    val createdAlarm: StateFlow<Alarm> = _createdAlarm


    fun clearCreatedAlarm() {
        _createdAlarm.value = createEmptyAlarm()
    }

    private val _insertedAlarm = MutableStateFlow<Alarm?>(null)
    val insertedAlarm: StateFlow<Alarm?> get() = _insertedAlarm

    private val _currentAlarmEndtime = MutableStateFlow(getNow())
    val currentAlarmEndtime: StateFlow<LocalDateTime> get() = _currentAlarmEndtime


    fun setCurrentAlarmEndtime(time: LocalDateTime){
        _currentAlarmEndtime.value = time
    }


    fun updateAlarm(alarmItem: Alarm) {
        val nextDate = calculateNextWakeupDateTime(alarmItem.endAlarm.time)
        val updatedDate = Alarm(
            //TODO
            //startAlarm = alarmItem.startAlarm,
            startAlarm = getNow(),
            endAlarm = nextDate
        )
        _createdAlarm.value  = updatedDate
        alarmRepository.updateAlarm(_createdAlarm.value)

    }

    fun deleteAlarm(alarmItem: Alarm) {
        viewModelScope.launch {
            alarmRepository.deleteAlarm(alarmItem)
            clearCreatedAlarm()
        }
    }


    fun insertAlarm() {
        viewModelScope.launch {
            val insertedAlarm = alarmRepository.insertAlarm(_createdAlarm.value)
            val fetchedAlarm = alarmRepository.getAlarmById(insertedAlarm)
            _insertedAlarm.value = fetchedAlarm
            _createdAlarm.value = createEmptyAlarm()
            _insertedAlarm.value?.let { alarm ->
                alarmScheduler.scheduleAlarm(alarm)
            }
        }
    }




}