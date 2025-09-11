package org.sleepapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sleepapp.data.model.Note

@Composable
fun NoteItem(note: Note){
    Column(Modifier.fillMaxWidth()
        .border(
            2.dp,
            Color.Red,
            RoundedCornerShape(8.dp))
    ){
        Text(note.createdAt.toString())
        Text(note.title)
        Text(note.content)
    }

}