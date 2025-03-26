package org.sleepapp.di

import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.sleepapp.data.database.RoomDb
import org.sleepapp.data.database.androidDatabaseBuilder

val androidDatabaseModule = module {
    single<RoomDatabase.Builder<RoomDb>> { androidDatabaseBuilder(androidContext()) }
}