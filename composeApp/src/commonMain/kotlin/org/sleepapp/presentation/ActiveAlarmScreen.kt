package org.sleepapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.sleepapp.data.model.Alarm

class ActiveAlarmScreen(private val alarm: Alarm) : Screen {
    @Composable
    override fun Content(){
        val navigatior = LocalNavigator.current
        Column {
            Text(alarm.id.toString())
            Text(alarm.endAlarm.toString())


            Text("Active alarm")
            Button(onClick = {navigatior?.pop()}){
                Text("Return to alarm screen")
            }

        }

    }


}