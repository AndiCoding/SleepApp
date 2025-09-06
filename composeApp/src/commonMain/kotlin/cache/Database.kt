package cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalTime
import org.example.sleepapp.AlarmDatabase
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.util.localTimeToString
import org.sleepapp.data.util.stringToLocalTime

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AlarmDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.alarmDatabaseQueries




    private val _alarmsFlow = MutableStateFlow<List<Alarm>>(emptyList())
    internal val alarmsFlow: StateFlow<List<Alarm>> get() = _alarmsFlow

    init {
        refreshAlarms()
    }

    private fun refreshAlarms() {
        _alarmsFlow.value = getAllAlarms()
    }

    internal fun addAlarm(
        startAlarm: LocalTime,
        endAlarm: LocalTime,
        interval: Long
    ) {
        dbQuery.insertAlarm(
            start_time = localTimeToString(startAlarm),
            end_time = localTimeToString(endAlarm),
            interval = interval
        )

        refreshAlarms()
    }

    internal fun updateAlarm(
        id: Long,
        startAlarm: LocalTime,
        endAlarm: LocalTime,
        interval: Long
    )  {
        dbQuery.updateAlarm(
            id = id,
            start_time = localTimeToString(startAlarm),
            end_time = localTimeToString(endAlarm),
            interval = interval

        )
        refreshAlarms()
    }


    internal fun deleteAlarm(id: Long) {
        dbQuery.deleteAlarm(id)
        refreshAlarms()
    }


    internal fun getAllAlarms(): List<Alarm> {
        return dbQuery.getAllAlarms().executeAsList().map { dbAlarm ->
            Alarm(
                id = dbAlarm.id,
                startAlarm = stringToLocalTime(dbAlarm.start_time),
                endAlarm = stringToLocalTime(dbAlarm.end_time),
                interval = dbAlarm.interval
            )
        }

    }
}