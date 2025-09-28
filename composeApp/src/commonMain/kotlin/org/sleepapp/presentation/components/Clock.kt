package org.sleepapp.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sleepapp.data.util.getNow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.atan2
import kotlin.text.append


fun Float.toRadians(): Double {
    return this * (PI / 180)
}

// Styling for Markers
data class DialStyle(
    val stepsWidth: Dp = 2.dp,
    val stepsColor: Color = Color.Black,
    val normalStepsLineHeight: Dp = 4.dp,
    val fiveStepsLineHeight: Dp = 8.dp,
    val stepsTextStyle: TextStyle = TextStyle(),
    val stepsLabelTopPadding: Dp = 12.dp,
    val boxSize: Double = 320.0
)


@Composable
fun Clock(
    initialTime: LocalTime,
    onClockChange: (LocalTime) -> Unit,
    dialStyle: DialStyle = DialStyle(),
    modifier: Modifier = Modifier
        .size(dialStyle.boxSize.dp)
        .border(2.dp,
            Color.Red,
            RectangleShape),
){
    val textMeasurer = rememberTextMeasurer()
    var minuteRotation by remember { mutableStateOf(0f) }
    var secondRotation by remember { mutableStateOf(0f) }
    val boxSize = dialStyle.boxSize

    val initialAngle = remember(initialTime) {
        convertLocalTimeToAngle(initialTime)
    }
    var angle by remember {
        mutableStateOf(initialAngle)
    }

    LaunchedEffect(angle){
        val time = convertAngleToLocalTime(angle)
        onClockChange(time)
    }

    Canvas(modifier = modifier.pointerInput(Unit){
        detectDragGestures { change, _ ->
            val center = Offset(size.width / 2f, size.height / 2f)
            val touch = change.position
            val dx = touch.x - center.x
            val dy = touch.y - center.y
            val theta = atan2(dy.toDouble(), dx.toDouble()) * 180 / PI
            angle = ((theta + 90 + 360) % 360).toFloat()
        }
    }){
        val outerRadius = minOf(this.size.width, this.size.height) / 2f
        val innerRadius = outerRadius - 40.dp.toPx()
        val centerX = size.width / 2
        val centerY = size.height / 2


        // Outer clock cirlce
        drawCircle(
            color = Color(0xFFF1F1F1),
            radius = outerRadius,
            center = center
        )

        // Inner clock circle
        drawCircle(
            color = Color(0xFFFFFFFF),
            radius = innerRadius,
            center = center
        )

        // Calculate blue circle position
        val blueRadius = (outerRadius + innerRadius) / 2
        val x = centerX + blueRadius * cos((angle - 90).toRadians())
        val y = centerY + blueRadius * sin((angle - 90).toRadians())

        // Time selector
        drawCircle(
            color = Color.Blue,
            radius = (outerRadius - innerRadius) / 2,
            center = Offset( x.toFloat() ,y.toFloat()),

        )

        // Hour and minute markers on the clock, uses drawscope to draw them
        markers(
            radius = innerRadius,
            rotation = minuteRotation,
            textMeasurer = textMeasurer,
            dialStyle = dialStyle
        )

        // Text that displays current angle
        drawText(
            textMeasurer = textMeasurer,
            text = angle.toString(),
            topLeft = Offset(
                boxSize.toFloat(),
                boxSize.toFloat()
            )
        )

    }
}

private fun angleToOffset(center: Offset, radius: Float, angle: Int, rotation: Float): Offset {
    val rad = ((angle + rotation - 90) * (PI / 180))
    return Offset(
        x = center.x + radius * cos(rad).toFloat(),
        y = center.y - radius * sin(rad).toFloat()
    )
}


private fun DrawScope.drawStep(
    start: Offset,
    end: Offset,
    color: Color,
    width: Float
){
    drawLine(
        color = color,
        start = start,
        end = end,
        strokeWidth = width,
        cap = StrokeCap.Round
    )
}

private fun DrawScope.drawStepLabel(
    textMeasurer: TextMeasurer,
    label: String,
    position: Offset,
    style: TextStyle
) {
    val layout = textMeasurer.measure(
        text = buildAnnotatedString { append(label) },
        style = style
    )
    val topLeft = Offset(
        position.x - layout.size.width / 2f,
        position.y - layout.size.height / 2f
    )
    drawText(
        textMeasurer = textMeasurer,
        text = label,
        topLeft = topLeft,
        style = style
    )
}


fun DrawScope.markers(
    radius: Float,
    rotation: Float,
    textMeasurer: TextMeasurer,
    dialStyle: DialStyle = DialStyle()
){
    var stepsAngle = 0
    repeat(60) { steps ->
        val isFiveStep = steps % 5 == 0
        val stepsHeight = if (isFiveStep) dialStyle.fiveStepsLineHeight.toPx() else dialStyle.normalStepsLineHeight.toPx()

        val start = angleToOffset(center, radius, stepsAngle, rotation)
        val end = angleToOffset(center, radius - stepsHeight, stepsAngle, rotation)
        drawStep(start, end, dialStyle.stepsColor, dialStyle.stepsWidth.toPx())

        if (isFiveStep) {
            val label = if (steps % 15 == 0) "6" else "2"
            val labelPos = angleToOffset(
                center,
                radius - stepsHeight - dialStyle.stepsLabelTopPadding.toPx(),
                stepsAngle,
                rotation
            )
            drawStepLabel(textMeasurer, label, labelPos, dialStyle.stepsTextStyle)
        }
        stepsAngle += 6
    }
}



private fun convertAngleToLocalTime(angle: Float): LocalTime {
    val normalized = ((angle % 360) + 360) % 360
    val hour = ((normalized / 15).toInt()) % 24
    val minute = ((normalized % 15) / 15 * 60).toInt()

    return LocalTime(
        hour = hour,
        minute = minute,
        second = 0,
        nanosecond = 0
    )
}

private fun convertLocalTimeToAngle(time: LocalTime): Float {
    val hourAngle = (time.hour % 24) * 15f
    val minuteAngle = (time.minute / 60f) * 15f
    return hourAngle + minuteAngle
}

@Preview
@Composable
fun ClockPreview(){
    Clock(
        initialTime = getNow().time,
        onClockChange = {})
}