package org.sleepapp.presentation.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sleepapp.data.model.Alarm

@Composable
fun SmallDateSummaryBox(
    onClick: () -> Unit,
    selected: Boolean = false,
    alarm: Alarm,
){
    val selected = mutableIntStateOf(if(selected) 92 else 80)
    Box(modifier = Modifier
        .height(selected.value.dp)
        .width(64.dp)
        .background(Color.Red)
    ){
        Text(alarm.endAlarm.toString())

    }
}