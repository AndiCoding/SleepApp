package org.sleepapp.presentation.alarm

import StoredAlarm
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.data.util.localDateTimetoHourAndMinute
import org.sleepapp.presentation.alarm.components.InputTimeSelector
import org.sleepapp.viewmodel.AlarmViewModel

@Composable
fun AlarmScreen() {
            val alarmViewModel = koinViewModel<AlarmViewModel>()
            val currentAlarm by alarmViewModel.currentAlarm.collectAsState()
            val alarmList by alarmViewModel.alarms.collectAsState()

            LaunchedEffect(alarmViewModel.latestInsertedAlarmId){
            }

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
                            Text(localDateTimetoHourAndMinute(currentAlarm.startAlarm))
                        }
                        Column {
                            Text("Wakeup", modifier = Modifier)
                            Text(localDateTimetoHourAndMinute(currentAlarm.endAlarm))
                        }
                    }

                        InputTimeSelector(
                            onConfirm = {
                                currentAlarm ->
                                alarmViewModel.setCurrentAlarmEndtime(currentAlarm)

                            },
                            onDismiss = { /*TODO*/ }
                        )
                }
                Button(onClick = {
                    alarmViewModel.insertAlarm()
                }){
                    Text("Activate")
                }
                Text("Recent Alarms", fontSize = 18.sp)

                LazyColumn(
                        Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight()) {
                        itemsIndexed(
                            items = alarmList
                        ) { index, alarm ->
                            alarm?.let {
                                StoredAlarm(
                                    alarm = alarm,
                                    {
                                        alarmViewModel.updateAlarm(alarm.id)
                                    },
                                    { alarmViewModel.deleteAlarm(alarm) })
                            }

                        }
                    }

            }
        }
