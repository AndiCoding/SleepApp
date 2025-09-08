package org.sleepapp.di

import app.cash.sqldelight.db.SqlDriver
import org.sleepapp.data.dao.AlarmDao
import cache.DatabaseDriverFactory
import org.example.sleepapp.AlarmDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sleepapp.data.dao.NotesDao
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
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() }
    single { AlarmDatabase(get<SqlDriver>()) }
    single { get<AlarmDatabase>().alarmDatabaseQueries }

    // DAOs and Repository
    singleOf(::AlarmDao)
    singleOf(::NotesDao)
    singleOf(::AlarmRepository)
    singleOf(::NoteRepository)

    // ViewModels
    viewModelOf(::AlarmViewModel)
    viewModelOf(::StatisticsViewModel)
    viewModelOf(::NotesViewModel)
    viewModelOf(::ProfileViewModel)

}