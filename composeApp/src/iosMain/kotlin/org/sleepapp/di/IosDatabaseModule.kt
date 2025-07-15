package org.sleepapp.di

import cache.Database
import cache.DatabaseDriverFactory
import cache.IOSDatabaseDriverFactory
import org.koin.dsl.module

val iosDatabaseModule = module {
    single<DatabaseDriverFactory>{ IOSDatabaseDriverFactory() }
    single{Database(databaseDriverFactory = get<DatabaseDriverFactory>())}
}