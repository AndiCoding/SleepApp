package org.sleepapp.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.sleepapp.data.AppDatabase
import org.sleepapp.data.getDatabaseBuilder
import org.sleepapp.data.repository.AlarmScheduler
import org.sleepapp.data.repository.AndroidAlarmScheduler

val androidDatabaseModule = module {
    single<AlarmScheduler>{AndroidAlarmScheduler(androidContext())}

}