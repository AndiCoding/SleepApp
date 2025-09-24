package org.sleepapp.presentation.screens.alarm.components

import androidx.compose.runtime.Composable
import kotlin.math.PI

fun toRadians(deg: Double): Double = deg / 180.0 * PI



@Composable
fun Clock() {/*
    val alarmViewModel = koinViewModel<AlarmViewModel>()
    val positions by alarmViewModel.positions.collectAsState() // Collect the StateFlow
    val startTime by alarmViewModel.startTime.collectAsState()
    val endTime by alarmViewModel.endTime.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        contentAlignment = Alignment.Center
        ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
            detectDragGestures(
                onDrag = { change, dragAmount ->
                    change.consume()

                    val newPosition = startTime + Offset(dragAmount.x, dragAmount.y)

                    // Snap to the nearest position
                    val nearestPosition = positions.values.minByOrNull { position ->
                        (position - newPosition).getDistance()
                    }
                    nearestPosition?.let {
                        alarmViewModel.updateStartTime(it)
                    }
                }
            )}){
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 2
            val adjustment = 70.dp.toPx()

            drawCircle(
                color = Color(0xFFF1F1F1),
                radius = radius * 0.9f,
                center = center
            )
            drawCircle(
                color = Color(0xFFFFFFFF),
                radius = radius *0.7f,
                center = center
            )

            // Drawing all the lines within the clock
            for (i in 0 until 60) {
                val angle = toRadians(i * 6.0)
                val lineLength = when {
                    i % 15 == 0 -> 5.dp.toPx()
                    i % 5 == 0 -> 5.dp.toPx()
                    else -> 2.dp.toPx()
                }
                val lineWidth = when {
                    i % 15 == 0 -> 2.dp.toPx()
                    i % 5 == 0 -> 1.dp.toPx()
                    else -> 0.5.dp.toPx()
                }
                val start = Offset(
                    x = center.x + (radius - lineLength - adjustment) * cos(angle).toFloat(),
                    y = center.y + (radius - lineLength - adjustment) * sin(angle).toFloat()
                )
                val end = Offset(
                    x = center.x + (radius - adjustment) * cos(angle).toFloat(),
                    y = center.y + (radius - adjustment) * sin(angle).toFloat()
                )

                drawLine(
                    color = Color.Black,
                    start = start,
                    end = end,
                    strokeWidth = lineWidth
                )
            }

            alarmViewModel.updatePositions(radius - adjustment / 2, center)

           for ((_, position) in positions) {
                drawCircle(
                    color = Color.Red,
                    radius = 10.dp.toPx(),
                    center = position
                )
            }

            // Draw draggable element
            drawCircle(
                color = Color.Blue,
                radius = 10.dp.toPx(),
                center = startTime
            )
        }

        Text(startTime.toString())
        }
        */
 }



