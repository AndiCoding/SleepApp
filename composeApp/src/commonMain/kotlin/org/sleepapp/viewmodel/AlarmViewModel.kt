package org.sleepapp.viewmodel

import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.plus
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.data.repository.AlarmScheduler
import org.sleepapp.data.util.createRandomDateTime
import org.sleepapp.data.util.getNow


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

    private val _currentAlarmEndtime = MutableStateFlow(getNow())
    val currentAlarmEndtime: StateFlow<LocalDateTime> get() = _currentAlarmEndtime


    fun setCurrentAlarmEndtime(time: LocalDateTime){
        _currentAlarmEndtime.value = time
    }

    fun updateAlarm(alarmItem: Alarm) {
        viewModelScope.launch {
            alarmRepository.updateAlarm(alarmItem)
        }
    }

    fun deleteAlarm(alarmItem: Alarm) {
        viewModelScope.launch {
            alarmRepository.deleteAlarm(alarmItem)
            clearCreatedAlarm()
        }
    }


    fun setAlarmAndNavigate(): Long {
        val id = mutableLongStateOf(0)
        val alarm = Alarm(
            //TODO
            startAlarm = createRandomDateTime(),
            //startAlarm = getNow(),
            endAlarm = _currentAlarmEndtime.value
        )

        viewModelScope.launch {
            id.value = alarmRepository.insertAlarm(alarm)
            alarmScheduler.scheduleAlarm(alarm)
            _createdAlarm.value = alarm.copy(id = id.value)
        }

        return id.value

    }

    fun insertAlarm(): Long {
        val id = mutableLongStateOf(0)


        val alarm = Alarm(

            startAlarm = getNow(),
            endAlarm = _currentAlarmEndtime.value
        )
        viewModelScope.launch {
            id.value = alarmRepository.insertAlarm(alarm)
            alarmScheduler.scheduleAlarm(alarm)
            _createdAlarm.value = alarm.copy(id = id.value)
        }
        return id.value
    }

    private fun calculateNextWakeupDateTime(wakeupTime: LocalTime): LocalDateTime{
        val currentDateTime = getNow()
        var nextWakeupDateTime = LocalDateTime(currentDateTime.date, wakeupTime)

        if (nextWakeupDateTime <= currentDateTime) {
            val tomorrow = currentDateTime.date.plus(1, DateTimeUnit.DAY)
            nextWakeupDateTime = LocalDateTime(tomorrow, wakeupTime)
        }

        return nextWakeupDateTime
    }
    fun createAlarmWithSameWakeupTime(wakeupTime: LocalDateTime): Alarm? {
        val wakeupTimeOnly = LocalTime(wakeupTime.hour, wakeupTime.minute)
        val nextWakeupDateTime = calculateNextWakeupDateTime(wakeupTimeOnly)

        _currentAlarmEndtime.value = nextWakeupDateTime

        insertAlarm()

        return createdAlarm.value
    }



}