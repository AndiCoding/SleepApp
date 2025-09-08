package org.sleepapp.viewmodel

import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
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
import org.sleepapp.data.state.AlarmStateHolder
import org.sleepapp.data.util.getNow
import kotlin.text.compareTo


class AlarmViewModel(
private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler,
    private val alarmStateHolder: AlarmStateHolder
) : ViewModel() {

    val createdAlarm = alarmStateHolder.createdAlarm
    val alarms = alarmStateHolder.alarms

    private val _currentAlarmEndtime = MutableStateFlow(getNow())
    val currentAlarmEndtime: StateFlow<LocalDateTime> get() = _currentAlarmEndtime

    override fun onCleared() {
        super.onCleared()
        alarmStateHolder.clear()
    }

    fun getCurrentAlarm(): Alarm? {
        return createdAlarm.value
    }
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
        }
    }


    fun getAlarmById(id: Long): Flow<Alarm?> {
        return alarmRepository.getAlarmById(id)
    }

    fun setAlarmAndNavigate(): Long {
        val id = mutableLongStateOf(0)
        val alarm = Alarm(
            startAlarm = getNow(),
            endAlarm = _currentAlarmEndtime.value
        )

        viewModelScope.launch {
            id.value = alarmRepository.insertAlarm(alarm)
            alarmScheduler.scheduleAlarm(alarm)
            alarmStateHolder.setCreatedAlarm(id.value)
        }

        return id.value

    }

    fun clearCreatedAlarm() {
        viewModelScope.launch {
            alarmStateHolder.clearCreatedAlarm()
        }
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
            alarmStateHolder.setCreatedAlarm(id.value)
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