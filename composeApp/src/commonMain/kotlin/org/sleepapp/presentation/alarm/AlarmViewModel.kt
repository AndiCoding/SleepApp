package org.sleepapp.presentation.alarm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.sleepapp.data.model.AlarmItem
import org.sleepapp.domain.DeleteAlarmUseCase
import org.sleepapp.domain.GetAllAlarmUseCase
import org.sleepapp.domain.InsertAlarmUseCase
import org.sleepapp.domain.UpdateAlarmUseCase

class AlarmViewModel(
    private val insertAlarmUseCase: InsertAlarmUseCase,
    private val updateAlarmUseCase: UpdateAlarmUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase,
    private val getAllAlarmUseCase: GetAllAlarmUseCase
) : ViewModel() {
    val anchorPoints = mutableListOf<Offset>()



    val insertAlarm = fun(alarmItem: AlarmItem) {
        viewModelScope.launch { insertAlarmUseCase(alarmItem) }
    }

    val updateAlarm = fun(alarmItem: AlarmItem) {
        viewModelScope.launch { updateAlarmUseCase(alarmItem) }
    }

    val deleteAlarm = fun(alarmItem: AlarmItem) {
        viewModelScope.launch { deleteAlarmUseCase(alarmItem) }
    }

    fun getAllAlarm() : Flow<List<AlarmItem>?>{
        return getAllAlarmUseCase()
    }



}