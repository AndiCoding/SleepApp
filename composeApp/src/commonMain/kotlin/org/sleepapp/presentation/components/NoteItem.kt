package org.sleepapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sleepapp.data.model.Note

@Composable
fun NoteItem(note: Note){
    Column(Modifier.fillMaxWidth()){
        Text(note.title)
        Text(note.content)
    }

}