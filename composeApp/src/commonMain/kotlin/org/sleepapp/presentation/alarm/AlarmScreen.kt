package org.sleepapp.presentation.alarm
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.sleepapp.data.model.AlarmItem
import org.sleepapp.presentation.alarm.components.Clock


class AlarmScreen : Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            val alarmViewModel = koinViewModel<AlarmViewModel>()
            val navigator: Navigator = LocalNavigator.currentOrThrow
            var showAddDialog by remember {
                mutableStateOf(false)
            }
            var showUpdateDialog by remember {
                mutableStateOf(false)
            }
            var selectedAlarm by remember{
                mutableStateOf<AlarmItem?>(null)
            }


            val alarms = remember {
                mutableStateListOf(
                    (1..100).map{
                        AlarmItem(
                            id = it,
                            start = "10:00",
                            end = "11:00",
                            interval = "1h",
                            //isOptionRevealed = false
                        )
                    }
                )
            }

            val alarmList by alarmViewModel
                .getAllAlarm()
                .collectAsStateWithLifecycle(
                    initialValue = null
                )


            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(){
                    FloatingActionButton(
                        modifier = Modifier.align(
                            Alignment.CenterEnd
                        ),
                        onClick = {
                            showAddDialog = true
                        },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add Alarm"
                            )
                        },
                    )
                }

                Clock()


                LazyColumn(Modifier.fillMaxSize()) {
                    itemsIndexed(
                        items = alarmList.orEmpty(),
                        ) { index, alarm ->
                        Text(alarm.id.toString())

                    }

                }



                if (showAddDialog){
                    AddAlarmDialog(
                        onDismiss = { showAddDialog = false },
                        onConfirm = alarmViewModel.insertAlarm
                    )
                }
                if (showUpdateDialog){
                    selectedAlarm?.let {
                        UpdateAlarmDialog(
                            alarmItem = it,
                            onDismiss = { showUpdateDialog = false },
                            onConfirm = alarmViewModel.updateAlarm
                        )
                    }
                }


            }
        }
    }
}



/*
* SwipableItemWithActions(
                            isRevealed = alarm.isOptionRevealed,
                            onExpanded = {
                                alarms[index] = listOf(alarm.copy(isOptionRevealed = true))
                            },
                            onCollapsed = {
                                alarms[index] = listOf(alarm.copy(isOptionRevealed = false))
                            },
                            actions = {
                                ActionIcon(
                                    onClick = {
                                        alarms[index] = listOf(alarm.copy(isOptionRevealed = false))
                                        alarmViewModel.deleteAlarm(alarm)
                                    },
                                    backgroundColor = Color.Red,
                                    icon = Icons.Default.Delete,
                                    modifier = Modifier.fillMaxHeight()
                                )
                                ActionIcon(
                                    onClick = {
                                        alarms[index] = listOf(alarm.copy(isOptionRevealed = false))
                                        selectedAlarm = alarm
                                        showUpdateDialog = true
                                    },
                                    backgroundColor = Color.Yellow,
                                    icon = Icons.Default.Edit,
                                    modifier = Modifier.fillMaxHeight()
                                )
                                ActionIcon(
                                    onClick = {
                                        alarms[index] = listOf(alarm.copy(isOptionRevealed = false))

                                    },
                                    backgroundColor = Color.Magenta,
                                    icon = Icons.Default.Share,
                                    modifier = Modifier.fillMaxHeight()
                                )
                            },
                            ){
                            ListItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                leadingContent = {
                                    Text(text = alarm.start)
                                },
                                trailingContent = {
                                    Text(text = alarm.interval)
                                },
                                headlineContent = {Text(alarm.id.toString())},
                            )
                        }
*
* */