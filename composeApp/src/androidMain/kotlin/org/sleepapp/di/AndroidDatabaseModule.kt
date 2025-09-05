package org.sleepapp.di

import cache.AndroidDatabaseDriverFactory
import cache.Database
import cache.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.sleepapp.data.repository.AlarmScheduler
import org.sleepapp.data.repository.AndroidAlarmScheduler

val androidDatabaseModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
    single{ Database(databaseDriverFactory = get<DatabaseDriverFactory>()) }
    single<AlarmScheduler>{AndroidAlarmScheduler(androidContext())}


}