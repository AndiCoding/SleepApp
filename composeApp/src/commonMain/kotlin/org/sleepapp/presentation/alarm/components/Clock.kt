package org.sleepapp.presentation.alarm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Clock() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text("Home", modifier = Modifier,
            fontSize = MaterialTheme.typography.titleLarge.fontSize)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ){
            Column {
                Text("Bedtime", modifier = Modifier)
                Text("10:30pm", modifier = Modifier)
            }
            Column {
                Text("Wakeup", modifier = Modifier)
                Text("10:30pm", modifier = Modifier)
            }

        }
        Box(modifier = Modifier
            .clip(CircleShape)
            .background(Color.Blue)
            .fillMaxWidth(0.67f)
            .aspectRatio(1f)
        ){
            Box(modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize(0.7f)
                .background(Color.Red)
                .align(Alignment.Center)

            )
        }
        TextButton( onClick = {},
            content = {
                Text("Set Alarm")
            },
        )
    }

}

@Preview
@Composable
fun ClockPreview() {
    Clock()
}