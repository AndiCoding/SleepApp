package org.sleepapp.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ClockTemplate(
    modifier: Modifier = Modifier.size(320.dp).border(2.dp,Color.Red, RectangleShape),
    clockStyle: ClockStyle = ClockStyle()
){
    val textMeasurer = rememberTextMeasurer()
    var minuteRotation by remember { mutableStateOf(0f) }
    var secondRotation by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = true){
        while (true){
            delay(16)
            secondRotation += 0.096f
        }
    }
    LaunchedEffect(key1 = true){
        while (true){
            delay(1000)
            minuteRotation -= 0.1f
        }
    }

    Canvas(modifier = modifier){
        val outerRadius = minOf(this.size.width, this.size.height) / 2f
        val innerRadius = outerRadius - 60.dp.toPx()

        markers(
            radius = outerRadius,
            rotation = secondRotation,
            textMeasurer = textMeasurer,
            dialStyle = clockStyle.dialStyle
        )

        markers(
            radius = innerRadius,
            rotation = minuteRotation,
            textMeasurer = textMeasurer,
            dialStyle = clockStyle.dialStyle
        )
    }

}


@Preview
@Composable
fun ClockTemplatePreview(){
ClockTemplate(
)
}