package org.sleepapp.presentation.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteCard(){
    Box(
        modifier = Modifier
            .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .padding(16.dp,8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            minLines = 2,
            maxLines = 4,
            text = "Life is a collection of moments, each uniquely beautiful. Embrace the unexpected, learn from challenges, and cherish the connections you make. Every sunrise brings new opportunities; every sunset offers reflection. Live fully, love deeply, and create memories that last a lifetime.",
            )

    }
}