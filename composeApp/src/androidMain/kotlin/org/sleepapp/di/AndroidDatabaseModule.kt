package org.sleepapp.di

import cache.AndroidDatabaseDriverFactory
import cache.Database
import cache.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDatabaseModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
    single{ Database(databaseDriverFactory = get<DatabaseDriverFactory>()) }

}