package org.sleepapp.di

import androidx.room.RoomDatabase
import cache.AndroidDatabaseDriverFactory
import cache.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDatabaseModule = module {
    single<Database> {
        Database(
            databaseDriverFactory = AndroidDatabaseDriverFactory(
                androidContext()
            )
        )
    }
}