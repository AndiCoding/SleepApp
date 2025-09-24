package org.sleepapp.presentation.screens.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlarmDetails(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Column(modifier = Modifier
            .fillMaxSize(0.9f)
            .background(Color.LightGray),
            verticalArrangement = Arrangement.Center
        ){
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text("Bedtime")
                Text("Wakeup")
                Text("Asleep for")
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text("23:00")
                Text("07:00")
                Text("8 hours")
            }

        }
    }
}