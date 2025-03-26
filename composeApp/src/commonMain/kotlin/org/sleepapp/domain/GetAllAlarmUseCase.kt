package org.sleepapp.domain

import kotlinx.coroutines.flow.Flow
import org.sleepapp.data.model.AlarmItem
import org.sleepapp.data.repository.AlarmRepository

class GetAllAlarmUseCase(
    private val alarmRepository: AlarmRepository
) {
    operator fun invoke(): Flow<List<AlarmItem>?>{
        return alarmRepository.getAllAlarms()
    }
}
