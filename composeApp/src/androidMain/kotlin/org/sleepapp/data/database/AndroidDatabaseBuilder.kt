package org.sleepapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun androidDatabaseBuilder(context: Context): RoomDatabase.Builder<RoomDb> {
    val dbFile = context.applicationContext.getDatabasePath("sleep_app.db")

    return Room.databaseBuilder(
        context,
        dbFile.absolutePath
    )
}