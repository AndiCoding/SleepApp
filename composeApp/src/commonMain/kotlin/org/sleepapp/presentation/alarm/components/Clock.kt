package org.sleepapp.presentation.alarm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun Clock() {
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
}
