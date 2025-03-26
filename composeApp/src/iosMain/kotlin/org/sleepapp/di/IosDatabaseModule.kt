package org.sleepapp.di

import androidx.room.RoomDatabase
import org.koin.dsl.module
import org.sleepapp.data.database.RoomDb
import org.sleepapp.data.database.iosDatabaseBuilder

val iosDatabaseModule = module {
    single<RoomDatabase.Builder<RoomDb>> { iosDatabaseBuilder() }
}