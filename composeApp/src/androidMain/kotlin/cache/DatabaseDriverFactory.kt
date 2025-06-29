package cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.sleepapp.AlarmDatabase

class AndroidDatabaseDriverFactory(private val context: Context): DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AlarmDatabase.Schema, context, "Alarm.db")
    }
}