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


    fun insertAlarm(alarm: Alarm = randomDate()) {
        viewModelScope.launch {
            val insertedAlarm = alarmRepository.insertAlarm(alarm)
            val fetchedAlarm = alarmRepository.getAlarmById(insertedAlarm)
            _createdAlarm.value = fetchedAlarm
            alarmScheduler.scheduleAlarm(fetchedAlarm)
        }
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


    //TODO: Randomizing date, in order to test if notes are
    // specific to dates (cleared when exiting an alarm)
    private fun randomDate(): Alarm {

        val newRandomDate: LocalDateTime = LocalDateTime(
            date = LocalDate(
                year = 1970,
                monthNumber = (1..12).random(),
                dayOfMonth = (1..28).random()
            ),
            time = _currentAlarmEndtime.value.time
        )
        return Alarm(
            startAlarm = newRandomDate,
            endAlarm = calculateNextWakeupDateTime(newRandomDate.time),
        )
    }

}