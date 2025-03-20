package org.sleepapp.presentation.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.viewmodel.NotesViewModel
import cafe.adriel.voyager.core.screen.Screen


class NotesScreen : Screen {

    @Composable
    override fun Content() {
        MaterialTheme {
            val notesViewModel = koinViewModel<NotesViewModel>()

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Notes Screen")
                Text(text = notesViewModel.sayHello("notes from view model"))

            }
        }
    }
}