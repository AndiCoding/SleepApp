package org.sleepapp.presentation.statistics.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.sleepapp.data.model.Alarm

@Composable
fun TimelineDisplay(){
       // List state for scrolling control
    val listState = rememberLazyListState()

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
    ){
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    state = listState,
                    flingBehavior = rememberSnapFlingBehavior( listState),
                    ){
                    items(12){ date ->
                        Spacer(Modifier.padding(12.dp))
                        SmallDateSummaryBox(
                            onClick = {
                            },
                            alarm = Alarm(
                                id = 0,
                                startAlarm = LocalDateTime(
                                    2023,
                                    1,
                                    1,
                                    12,
                                    0,
                                ),
                                endAlarm = LocalDateTime(
                                    2023,
                                    1,
                                    1,
                                    12,
                                    30
                                )
                            ),
                        )

                    }
    }
}
}