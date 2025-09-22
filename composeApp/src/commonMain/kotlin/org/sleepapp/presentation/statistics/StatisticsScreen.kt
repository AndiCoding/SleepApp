package org.sleepapp.presentation.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.sleepapp.presentation.statistics.components.AlarmDetails
import org.sleepapp.presentation.statistics.components.TimelineDisplay
import org.sleepapp.viewmodel.NotesViewModel
import org.sleepapp.viewmodel.StatisticsViewModel


class StatisticsScreen : Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            val statisticsViewModel = koinViewModel<StatisticsViewModel>()
            val notesViewModel = koinViewModel<NotesViewModel>()
            val selectedDate  = statisticsViewModel.selectedDate.collectAsState()
            val dateList = statisticsViewModel.dateList.collectAsState()
            val notes = notesViewModel.notesByDate.collectAsState()

            LaunchedEffect(selectedDate.value) {
                val localDateTimeConverted = LocalDateTime(
                    date = selectedDate.value,
                    time = LocalTime(0,0,0,0),
                )
                notesViewModel.getNotesByDate(localDateTimeConverted)
            }

            Column(modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "2 day streak",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(24.dp))
                TimelineDisplay(onDateSelected = { localDate ->
                    statisticsViewModel.updateSelectedDate(localDate)
                },
                    dateList,
                    selectedDate
                )

                Spacer(Modifier.height(24.dp))
                AlarmDetails()

                Spacer(Modifier.height(24.dp))
                Text("Notes",
                    style = MaterialTheme.typography.titleMedium)
                Text(selectedDate.value.toString())

                LazyColumn {
                    itemsIndexed(notes.value) { index, note ->
                        Row {
                            Text(note.title)
                            Text(note.content)
                        }

                    }
                }


                //TODO Choosing notes to show
                /*
                SelectedDayNotes { note ->
                    navigator.push(NoteScreen, note)
                }
                */


            }
        }
    }
}
