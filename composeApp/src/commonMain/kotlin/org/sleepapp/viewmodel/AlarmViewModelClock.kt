package org.sleepapp.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.sleepapp.data.model.AlarmItem
import org.sleepapp.data.repository.AlarmRepository

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun toRadians(deg: Double): Double = deg / 180.0 * PI

class AlarmViewModelClock(
    private val alarmRepository: AlarmRepository
) : ViewModel() {
    private val timePoints = List(60) { it }
    private val _positions = MutableStateFlow<Map<Int, Offset>>(emptyMap())
    val positions: StateFlow<Map<Int, Offset>> = _positions

    private val _startTime = MutableStateFlow(Offset(0f,0f))
    val startTime: StateFlow<Offset> = _startTime

    private val _endTime = MutableStateFlow(Offset(0f,0f))
    val endTime: StateFlow<Offset> = _endTime

    fun updateStartTime(startTime: Offset) {
        _startTime.value = startTime
    }

    fun updateEndTime(endTime: Offset) {
        _endTime.value = endTime
    }

    fun updatePositions(radius: Float, center: Offset) {
        val newPositions = mutableMapOf<Int, Offset>()
        timePoints.forEach { point ->
            newPositions[point] = calculatePosition(point, radius, center)
        }
        _positions.value = newPositions
    }

    // Function to calculate the position of a time point on the clock
    private fun calculatePosition(point: Int, radius: Float, center: Offset): Offset {
        val angle = toRadians(point * 6.0) // Each point is 6 degrees
        return Offset(
            x = center.x + radius * cos(angle).toFloat(),
            y = center.y + radius * sin(angle).toFloat()
        )
    }

    val insertAlarm = fun(alarmItem: AlarmItem) {
        viewModelScope.launch { alarmRepository.insertAlarm(alarmItem) }
    }

    val updateAlarm = fun(alarmItem: AlarmItem) {
        viewModelScope.launch { alarmRepository.updateAlarm(alarmItem) }
    }

    val deleteAlarm = fun(alarmItem: AlarmItem) {
        viewModelScope.launch { alarmRepository.deleteAlarm(alarmItem) }
    }

    fun getAllAlarm() : Flow<List<AlarmItem>?>{
        return alarmRepository.getAllAlarms()
    }

}