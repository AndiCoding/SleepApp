package org.sleepapp.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.sleepapp.data.AppDatabase
import org.sleepapp.data.repository.AlarmScheduler
import org.sleepapp.data.repository.AndroidAlarmScheduler

val androidDatabaseModule = module {
    single<AlarmScheduler>{AndroidAlarmScheduler(androidContext())}
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        )
    }

}