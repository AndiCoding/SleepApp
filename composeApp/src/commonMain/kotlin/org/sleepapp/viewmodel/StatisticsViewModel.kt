package org.sleepapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.sleepapp.presentation.alarm.components.Clock



class StatisticsViewModel() : ViewModel() {
    private val _selectedDate = MutableStateFlow(
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
    val selectedDate: MutableStateFlow<LocalDate> = _selectedDate

    private val _dateList = MutableStateFlow(generateDateList(_selectedDate.value))
    val dateList: StateFlow<List<LocalDate>> = _dateList

    fun updateSelectedDate(localDate: LocalDate) {
        _selectedDate.value = localDate
        _dateList.value = generateDateList(localDate)
    }


    private fun generateDateList(center: LocalDate): List<LocalDate> =
        (-5..5).map { offset -> center.plus(DatePeriod(days = offset)) }



}