package org.sleepapp.domain

import org.sleepapp.data.model.AlarmItem
import org.sleepapp.data.repository.AlarmRepository

class InsertAlarmUseCase(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(alarmItem: AlarmItem) {
        alarmRepository.insertAlarm(alarmItem = alarmItem)
    }
}