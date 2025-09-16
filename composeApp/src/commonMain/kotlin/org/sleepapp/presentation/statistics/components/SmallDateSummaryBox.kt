package org.sleepapp.presentation.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import org.sleepapp.data.model.Alarm

@Composable
fun SmallDateSummaryBox(
    onClick: () -> Unit,
    date: LocalDate,
){
   // val selected = mutableIntStateOf(if(selected) 92 else 80)

    Box(modifier = Modifier
        .height(80.dp)
        .width(64.dp)
        .background(Color.Red)
        .clickable { onClick() }
    ){
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(date.dayOfMonth.toString())
            Text(date.month.toString().take(3))
        }

    }
}