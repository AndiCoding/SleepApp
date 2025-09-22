package org.sleepapp.di

import androidx.room.Room
import org.sleepapp.data.dao.AlarmDao
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sleepapp.data.AppDatabase
import org.sleepapp.data.dao.NoteDao
import org.sleepapp.data.getRoomDatabase
import org.sleepapp.data.repository.AlarmRepository
import org.sleepapp.data.repository.NoteRepository
import org.sleepapp.getPlatform
import org.sleepapp.viewmodel.AlarmViewModel
import org.sleepapp.viewmodel.NotesViewModel
import org.sleepapp.viewmodel.ProfileViewModel
import org.sleepapp.viewmodel.StatisticsViewModel



val appModule = module {

    // Platform
    factory { getPlatform(this) }

    // Database
    singleOf(::getRoomDatabase)
    single<AlarmDao> {get<AppDatabase>().getAlarmDao()}
    single<NoteDao> {get<AppDatabase>().getNoteDao()}

    // Repository
    singleOf(::AlarmRepository)
    singleOf(::NoteRepository)

    // ViewModels
    viewModelOf(::AlarmViewModel)
    viewModelOf(::StatisticsViewModel)
    viewModelOf(::NotesViewModel)
    viewModelOf(::ProfileViewModel)

}