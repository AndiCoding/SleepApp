package org.sleepapp.presentation.alarm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun DraggableSnake(
    modifier: Modifier = Modifier,
    initialLength: Float = 0f,
    onValueChange: (Float) -> Unit = {},

){
    var length by remember { mutableStateOf(initialLength) }
    var start by remember { mutableStateOf(Offset(0f, 0f)) }
    var end by remember { mutableStateOf(Offset(0f, 0f)) }


    Box(
        modifier = modifier
            .size(300.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        start = Offset(offset.x, offset.y)
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        end = Offset(dragAmount.x, dragAmount.y)
                        length = sqrt((end.x - start.x).pow(2) + (end.y - start.y).pow(2))
                        onValueChange(length)
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(
                color = Color.Black,
                start = Offset(start.x, start.y),
                end = Offset(end.x, end.y),
                strokeWidth = 5f
            )
        }
    }
}