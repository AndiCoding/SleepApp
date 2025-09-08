package org.sleepapp.presentation.statistics.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectedDayNotes(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.7f)
        .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
        .padding(32.dp, 8.dp)
    ){
        LazyColumn {
            items(5){ index ->
                Spacer(modifier = Modifier.height(16.dp))
                NoteCard()
            }

        }
    }
}