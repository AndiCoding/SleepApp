package org.sleepapp.di

import cache.Database
import cache.DatabaseDriverFactory
import cache.IOSDatabaseDriverFactory
import org.koin.dsl.module
import org.sleepapp.data.repository.AlarmScheduler
import org.sleepapp.data.repository.IOSAlarmScheduler

val iosDatabaseModule = module {
    single<DatabaseDriverFactory>{ IOSDatabaseDriverFactory() }
    single{Database(databaseDriverFactory = get<DatabaseDriverFactory>())}
    //single<AlarmScheduler>{ IOSAlarmScheduler()}
}