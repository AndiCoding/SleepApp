package org.sleepapp.di

import androidx.room.RoomDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sleepapp.data.database.CreateDatabase
import org.sleepapp.data.database.RoomDb
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.presentation.alarm.AlarmViewModel
import org.sleepapp.presentation.statistics.StatisticsViewModel
import org.sleepapp.presentation.notes.NotesViewModel
import org.sleepapp.presentation.profile.ProfileViewModel

import org.sleepapp.domain.InsertAlarmUseCase
import org.sleepapp.domain.GetAllAlarmUseCase
import org.sleepapp.domain.UpdateAlarmUseCase
import org.sleepapp.domain.DeleteAlarmUseCase


import org.sleepapp.getPlatform

val appModule = module {

    factory { getPlatform(this) }

    //Database
    single<RoomDb> { CreateDatabase(get()).getDatabase() }

    // Repository
    singleOf(::AlarmRepository)

    // Use cases
    singleOf(::InsertAlarmUseCase)
    singleOf(::UpdateAlarmUseCase)
    singleOf(::DeleteAlarmUseCase)
    singleOf(::GetAllAlarmUseCase)

    // ViewModels
    viewModelOf(::AlarmViewModel)
    viewModelOf(::StatisticsViewModel)
    viewModelOf(::NotesViewModel)
    viewModelOf(::ProfileViewModel)

}