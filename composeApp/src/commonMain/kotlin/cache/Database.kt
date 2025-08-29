package cache


import org.example.sleepapp.AlarmDatabase
import org.sleepapp.data.model.Alarm



internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AlarmDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.alarmDatabaseQueries

    internal fun addAlarm(
        startHour: Long,
        startMinute: Long,
        endHour: Long,
        endMinute: Long,
    ) {
        dbQuery.insertAlarm(
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute,
        )
    }

    internal fun updateAlarm(
        id: Long,
        startHour: Long,
        startMinute: Long,
        endHour: Long,
        endMinute: Long,
    )  {
        dbQuery.updateAlarm(
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute,
            id = id
        )
    }


    internal fun deleteAlarm(id: Long) {
        dbQuery.deleteAlarm(id)
    }

    internal fun getAllAlarms(): List<Alarm> {
        return dbQuery.getAllAlarms().executeAsList().map { dbAlarm ->
            Alarm(
                id = dbAlarm.id,
                startHour = dbAlarm.startHour,
                startMinute = dbAlarm.startMinute,
                endHour = dbAlarm.endHour,
                endMinute = dbAlarm.endMinute,
                interval = "NOT IMPLEMENTED"
                //interval = dbAlarm.interval
            )
        }
        /*
         */
    }
}