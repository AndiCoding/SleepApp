package org.sleepapp.presentation.alarm
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.sleepapp.data.model.AlarmItem
import org.sleepapp.presentation.alarm.components.InputTimeSelector
import org.sleepapp.viewmodel.AlarmViewModel


class AlarmScreen : Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            val alarmViewModel = koinViewModel<AlarmViewModel>()
            val navigator: Navigator = LocalNavigator.currentOrThrow
            val currentAlarm by alarmViewModel.currentAlarm.collectAsState()

            var selectedHour by remember {
                mutableIntStateOf(0)
            }
            var selectedMinute by remember {
                mutableIntStateOf(0)
            }



            val alarmList = alarmViewModel
                .getAllAlarm()


            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Home", modifier = Modifier, fontSize = 24.sp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column {
                            Text("Bedtime", modifier = Modifier)
                            Text(alarmViewModel.getCurrentTime(), modifier = Modifier)
                        }
                        Column {
                            Text("Wakeup", modifier = Modifier)
                            Text("${currentAlarm.first}:${currentAlarm.second}", modifier = Modifier)
                        }
                    }

                        InputTimeSelector(
                            onConfirm = {
                                selectedHour, selectedMinute ->
                                alarmViewModel.setCurrentAlarm(selectedHour, selectedMinute)
                            },
                            onDismiss = { /*TODO*/ }
                        )
                }
                Button(onClick = {
                }){
                    Text("Activate")
                }

                Box(){
                    Text("Recent Alarms", fontSize = 18.sp)
                    LazyColumn(Modifier.fillMaxWidth()) {
                        itemsIndexed(
                            items = alarmList.orEmpty(),
                        ) { index, alarm ->
                            Text(alarm.id.toString())
                        }
                    }

                }




            }
        }
    }
}
