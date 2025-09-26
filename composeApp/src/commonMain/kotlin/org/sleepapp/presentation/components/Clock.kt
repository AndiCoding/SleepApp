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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


fun Float.toRadians(): Double {
    return this * (PI / 180)
}

data class DialStyle(
    val stepsWidth: Dp = 2.dp,
    val stepsColor: Color = Color.Black,
    val normalStepsLineHeight: Dp = 4.dp,
    val fiveStepsLineHeight: Dp = 8.dp,
    val stepsTextStyle: TextStyle = TextStyle(),
    val stepsLabelTopPadding: Dp = 12.dp,
    val boxSize: Double = 320.0
)

data class ClockStyle(
    val dialStyle: DialStyle = DialStyle()
)


@Composable
fun Clock(
    clockStyle: ClockStyle = ClockStyle(),
    modifier: Modifier = Modifier
        .size(clockStyle.dialStyle.boxSize.dp)
        .border(2.dp,
            Color.Red,
            RectangleShape),
){
    val textMeasurer = rememberTextMeasurer()
    var minuteRotation by remember { mutableStateOf(0f) }
    var secondRotation by remember { mutableStateOf(0f) }
    val boxSize = clockStyle.dialStyle.boxSize

    var angle by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(Unit){
        while(true){
            angle += 1f
            if(angle >= 360f) angle = 0f
            delay(16)
        }
    }

    Canvas(modifier = modifier){
        val outerRadius = minOf(this.size.width, this.size.height) / 2f
        val innerRadius = outerRadius - 40.dp.toPx()
        val centerX = size.width / 2
        val centerY = size.height / 2
        val x = centerX + outerRadius * cos(angle.toRadians()) / 2
        val y = centerY + outerRadius * sin(angle.toRadians()) / 2



        drawCircle(
            color = Color(0xFFF1F1F1),
            radius = outerRadius,
            center = center
        )
        drawCircle(
            color = Color(0xFFFFFFFF),
            radius = innerRadius,
            center = center
        )

        drawCircle(
            color = Color.Blue,
            radius = (outerRadius - innerRadius) / 2,
            center = Offset(x.toFloat(), y.toFloat())
        )

        markers(
            radius = innerRadius,
            rotation = minuteRotation,
            textMeasurer = textMeasurer,
            dialStyle = clockStyle.dialStyle
        )

    }
}



fun DrawScope.markers(
    radius: Float,
    rotation: Float,
    textMeasurer: TextMeasurer,
    dialStyle: DialStyle = DialStyle()
){
    var stepsAngle = 0
    repeat(60){ steps ->
        val stepsHeight = if (steps % 5 == 0){
            dialStyle.fiveStepsLineHeight.toPx()
        } else {
            dialStyle.normalStepsLineHeight.toPx()
        }

        val stepsStartOffset = Offset(
            x = center.x + (
                    radius * cos((stepsAngle + rotation)
                            * (PI / 180f))).toFloat(),
            y = center.y - (
                    radius * sin((stepsAngle + rotation)
                            * (PI / 180))).toFloat()
        )

        val stepsEndOffset = Offset(
            x = center.x + (radius - stepsHeight) * cos(
                (stepsAngle + rotation) * (PI / 180)
            ).toFloat(),
            y = center.y - (radius - stepsHeight) * sin(
                (stepsAngle + rotation) * (PI / 180)
            ).toFloat()
        )

        //draw step
        drawLine(
            color = dialStyle.stepsColor,
            start = stepsStartOffset,
            end = stepsEndOffset,
            strokeWidth = dialStyle.stepsWidth.toPx(),
            cap = StrokeCap.Round
        )


        if (steps % 5 == 0) {
            val stepsLabel = if (steps % 15 == 0){
                "6"
            } else {
                "2"
            }
            val stepsLabelTextLayout = textMeasurer.measure(
                text = buildAnnotatedString { append(stepsLabel) },
                style = dialStyle.stepsTextStyle
            )
            //calculate the offset
            val stepsLabelOffset = Offset(
                x = center.x + (radius - stepsHeight - dialStyle.stepsLabelTopPadding.toPx()) * cos(
                    (stepsAngle + rotation) * (PI / 180)
                ).toFloat(),
                y = center.y - (radius - stepsHeight - dialStyle.stepsLabelTopPadding.toPx()) * sin(
                    (stepsAngle + rotation) * (PI / 180)
                ).toFloat()
            )

            //subtract the label width and height to position label at the center of the step
            val stepsLabelTopLeft = Offset(
                stepsLabelOffset.x - ((stepsLabelTextLayout.size.width) / 2f),
                stepsLabelOffset.y - (stepsLabelTextLayout.size.height / 2f)
            )

            drawText(
                textMeasurer = textMeasurer,
                text = stepsLabel,
                topLeft = stepsLabelTopLeft,
                style = dialStyle.stepsTextStyle
            )
        }

        stepsAngle += 6
    }
}

@Preview
@Composable
fun ClockPreview(){
    Clock()
}