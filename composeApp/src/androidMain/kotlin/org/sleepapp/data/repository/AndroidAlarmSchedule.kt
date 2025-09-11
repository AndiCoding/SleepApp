package org.sleepapp.data.repository

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import org.sleepapp.AlarmReceiver
import org.sleepapp.data.model.Alarm

class AndroidAlarmScheduler(private val context: Context): AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

        override fun scheduleAlarm(alarm: Alarm) {
            val intent = Intent(context, AlarmReceiver::class.java).apply {
                putExtra("EXTRA_MESSAGE", alarm.id)
            }

            val timeTotrigger =  alarm.endAlarm.toInstant(UtcOffset.ZERO).epochSeconds

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeTotrigger,
            PendingIntent.getBroadcast(
                context,
                alarm.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
    override fun cancelAlarm(alarm: Alarm) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarm.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}