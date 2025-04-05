package org.sleepapp.presentation.alarm.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.presentation.alarm.AlarmViewModel
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun toRadians(deg: Double): Double = deg / 180.0 * PI


@Composable
fun Clock() {
    val alarmViewModel = koinViewModel<AlarmViewModel>()
    val density = LocalDensity.current
    val scaleFactor = 1.5f

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text("Home", modifier = Modifier,
            fontSize = MaterialTheme.typography.titleLarge.fontSize)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            contentAlignment = Alignment.Center
        ){
            Text("9hr 15min")
            Canvas(
                modifier = Modifier
                .fillMaxSize(0.5f),){
                with(density) {
                    val minuteLength = 4.dp.toPx()
                    val fiveMinuteLength = 10.dp.toPx()
                    val fifteenMinuteLength = 10.dp.toPx()
                    val minuteWidth = 2.dp.toPx()
                    val fiveMinuteWidth = 3.dp.toPx()
                    val fifteenMinuteWidth = 4.dp.toPx()
                    val radius = size.minDimension / 2
                    val center = Offset(size.width / 2, size.height / 2)

                    drawCircle(
                        color = Color(0xFFF1F1F1),
                        radius = radius * scaleFactor,
                        center = center
                    )
                    drawCircle(
                        color = Color(0xFFFFFFFF),
                        radius = radius * 1.05f,
                        center = center
                    )
                    for (i in 0 until 60) {
                        val angle = toRadians((i * 6).toDouble())
                        val startX = center.x + radius * cos(angle).toFloat()
                        val startY = center.y + radius * sin(angle).toFloat()

                        val (endX, endY, strokeWidth) = when {
                            i % 15 == 0 -> {
                                val endX = center.x + (radius - fifteenMinuteLength) * cos(angle).toFloat()
                                val endY = center.y + (radius - fifteenMinuteLength) * sin(angle).toFloat()
                                Triple(endX, endY, fifteenMinuteWidth)
                            }
                            i % 5 == 0 -> {
                                val endX = center.x + (radius - fiveMinuteLength) * cos(angle).toFloat()
                                val endY = center.y + (radius - fiveMinuteLength) * sin(angle).toFloat()
                                Triple(endX, endY, fiveMinuteWidth)
                            }
                            else -> {
                                val endX = center.x + (radius - minuteLength) * cos(angle).toFloat()
                                val endY = center.y + (radius - minuteLength) * sin(angle).toFloat()
                                Triple(endX, endY, minuteWidth)
                            }
                        }

                        alarmViewModel.anchorPoints.add(Offset(endX, endY))


                        drawLine(
                            color = Color(0xFF222222),
                            start = Offset(startX, startY),
                            end = Offset(endX, endY),
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Square
                        )


                    }

                    val scaledAnchorPoints = alarmViewModel.anchorPoints.map { point ->
                        val vector = point - center
                        center + vector * scaleFactor
                    }
                    drawPoints(
                        points = scaledAnchorPoints,
                        color = Color(0xFF222222),
                        pointMode = PointMode.Points,
                        strokeWidth = 10f,
                        cap = StrokeCap.Square
                    )
                }
            }
            DraggableSnake()
        }
        TextButton( onClick = {},
            content = {
                Text("Set Alarm")
            },
        )
    }
}