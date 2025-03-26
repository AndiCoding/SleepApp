package org.sleepapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.sleepapp.data.dao.AlarmDao
import org.sleepapp.data.model.AlarmItem

@Database(
    entities = [
        AlarmItem::class
    ],
    version = 2,
    exportSchema = true
)

@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomDb>{
    override fun initialize(): RoomDb
}