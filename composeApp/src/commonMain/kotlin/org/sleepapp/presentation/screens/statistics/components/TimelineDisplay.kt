package org.sleepapp.presentation.screens.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment


@Composable

fun TimelineDisplay(
    onDateSelected: (LocalDate) -> Unit,
    dateList: State<List<LocalDate>>,
    selectedDate: State<LocalDate>
){
    val listState = rememberLazyListState()
    val scrollScope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        state = listState
        ){
            itemsIndexed(dateList.value) { index, item ->
                val isSelected = selectedDate.value == item
                val sizeValue = if (isSelected) 92.dp else 80.dp
                Box(modifier = Modifier
                    .background(Color.Red)
                    .size(48.dp,sizeValue)
                    .clickable {
                        onDateSelected(item)
                        scrollScope.launch {
                            listState.animateScrollToItem(index, 0)
                        }
                    },
                ){
                    Column(
                        Modifier.fillMaxSize().padding(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(item.dayOfWeek.toString().take(3)
                            .lowercase()
                            .replaceFirstChar { it.uppercase()
                            })
                        Text(item.dayOfMonth.toString())
                        SleepQualityCircle()
                    }

                }

            }
        }
}