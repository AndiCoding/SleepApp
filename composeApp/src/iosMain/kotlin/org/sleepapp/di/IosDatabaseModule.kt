package org.sleepapp.di

import androidx.room.RoomDatabase
import org.koin.dsl.module
import org.sleepapp.data.AppDatabase
import org.sleepapp.data.getDatabaseBuilder

val iosDatabaseModule = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder()
    }
    //single<AlarmScheduler>{ IOSAlarmScheduler()}
}