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
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.util.createRandomDateTime
import org.sleepapp.data.util.localDateTimeToString
import org.sleepapp.data.util.localDateTimetoHourAndMinute
import org.sleepapp.presentation.components.NoteItem
import org.sleepapp.viewmodel.AlarmViewModel
import org.sleepapp.viewmodel.NotesViewModel

class ActiveAlarmScreen(private val alarm: Alarm) : Screen {

    @Composable
    override fun Content(){
        val alarmViewmodel = koinViewModel<AlarmViewModel>()
        val notesViewModel = koinViewModel<NotesViewModel>()
        val navigatior = LocalNavigator.current
        val notesByDate = notesViewModel.notesByDate.collectAsState()

        LaunchedEffect(alarm){
            notesViewModel.getNotesByDate(alarm.startAlarm)
        }

        // Clear notes when screen is destroyed
        DisposableEffect(Unit) {
            onDispose {
                notesViewModel.clearNotesByDate()
            }
        }
        Column {
            Text(localDateTimeToString(alarm.startAlarm))
            Text(localDateTimetoHourAndMinute(alarm.startAlarm))
            Text(localDateTimetoHourAndMinute(alarm.endAlarm))

            Text("Active alarm")
            Button(onClick = {navigatior?.pop()
                alarmViewmodel.clearCreatedAlarm()
            }){
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