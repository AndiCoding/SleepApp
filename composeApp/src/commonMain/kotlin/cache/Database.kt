package cache

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDateTime
import org.example.sleepapp.AlarmDatabase
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.util.localDateTimeToString
import org.sleepapp.data.util.stringToLocalDateTime

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
        startAlarm: LocalDateTime,
        endAlarm: LocalDateTime,
        interval: Long
    ) {
        dbQuery.insertAlarm(
            start_time = localDateTimeToString(startAlarm),
            end_time = localDateTimeToString(endAlarm),
            interval = interval
        )

        refreshAlarms()
    }

    internal fun updateAlarm(
        id: Long,
        startAlarm: LocalDateTime,
        endAlarm: LocalDateTime,
        interval: Long
    )  {
        dbQuery.updateAlarm(
            id = id,
            start_time = localDateTimeToString(startAlarm),
            end_time = localDateTimeToString(endAlarm),
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
                startAlarm = stringToLocalDateTime(dbAlarm.start_time),
                endAlarm = stringToLocalDateTime(dbAlarm.end_time),
                interval = dbAlarm.interval
            )
        }

    }
}