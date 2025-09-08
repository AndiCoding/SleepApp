package org.sleepapp.presentation.activealarm

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.util.localDateTimetoHourAndMinute
import org.sleepapp.viewmodel.AlarmViewModel

class ActiveAlarmScreen(private val alarm: Alarm) : Screen {
    @Composable
    override fun Content(){
        val alarmViewmodel = koinViewModel<AlarmViewModel>()
        val navigatior = LocalNavigator.current

        Column {
            Text(localDateTimetoHourAndMinute(alarm.startAlarm))
            Text(localDateTimetoHourAndMinute(alarm.endAlarm))

            Text("Active alarm")
            Button(onClick = {navigatior?.pop()}){
                Text("Return to alarm screen")
            }



        }

    }


}