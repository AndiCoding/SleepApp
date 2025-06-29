package org.sleepapp.di

import cache.Database
import cache.IOSDatabaseDriverFactory
import org.koin.dsl.module

val iosDatabaseModule = module {
    single<Database> { Database(
        databaseDriverFactory = IOSDatabaseDriverFactory()
    ) }
}