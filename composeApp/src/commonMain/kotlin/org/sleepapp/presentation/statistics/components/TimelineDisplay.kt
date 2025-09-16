package org.sleepapp.presentation.statistics.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import androidx.compose.runtime.State


@Composable

fun TimelineDisplay(
    onDateSelected: (LocalDate) -> Unit,
    dateList: State<List<LocalDate>>
){

       // List state for scrolling control
    val listState = rememberLazyListState()
    val scrollScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
    ){
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = listState,
            flingBehavior = rememberSnapFlingBehavior( listState)
            ){
                items(dateList.value.size, key = { it }) { index ->
                    Spacer(Modifier.padding(12.dp))
                    SmallDateSummaryBox(
                        onClick = {
                            onDateSelected(dateList.value[index])
                            scrollScope.launch {
                                listState.animateScrollToItem(index, -400)
                            }
                        },
                        date = dateList.value[index],
                        )

                    }
    }
}
}