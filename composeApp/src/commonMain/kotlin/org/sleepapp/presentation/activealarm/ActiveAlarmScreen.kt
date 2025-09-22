package org.sleepapp.presentation.activealarm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.presentation.components.NoteItem
import org.sleepapp.viewmodel.ActiveAlarmViewModel
import org.sleepapp.viewmodel.NotesViewModel

@Composable
fun ActiveAlarmScreen(alarmId: Long){
        val activeAlarmViewModel = koinViewModel<ActiveAlarmViewModel>()
        val notesViewModel = koinViewModel<NotesViewModel>()
        val notesByDate = notesViewModel.notesByDate.collectAsState()
        val activeAlarm = activeAlarmViewModel.activeAlarm.collectAsState()

        LaunchedEffect(Unit) {
            activeAlarmViewModel.getAlarmById(alarmId)
        }

        LaunchedEffect(activeAlarm.value){
            activeAlarm.value?.let { alarm ->
                notesViewModel.getNotesByDate(alarm.startAlarm)
            }
        }

        // Clear notes when screen is destroyed
        DisposableEffect(Unit) {
            onDispose {
                notesViewModel.clearNotesByDate()
            }
        }
        Column {
            Text(activeAlarm.value?.startAlarm.toString())
            Text(activeAlarm.value?.endAlarm.toString())
            Text("Active alarm")
            Button(onClick = {}){
                Text("Return to alarm screen")
            }
            TextField(
                value = notesViewModel.currentNote.value.title ,
                onValueChange = { newValue ->
                    notesViewModel.setTitle(newValue)
                })
            TextField(
                value = notesViewModel.currentNote.value.content ,
                onValueChange = { newValue ->
                    notesViewModel.setContent(newValue)
                })
            activeAlarm.value?.let { alarm ->
                Button(onClick = { notesViewModel.insertNote(alarm.startAlarm)}){
                    Text("Insert Note")
                }
                LazyColumn {
                    items(notesByDate.value.size) { index ->
                        NoteItem(note = notesByDate.value[index])
                    }
                }
            }

        }

    }

