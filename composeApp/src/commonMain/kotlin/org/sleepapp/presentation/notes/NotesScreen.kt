package org.sleepapp.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.viewmodel.NotesViewModel

@Composable
fun NotesScreen() {
            val notesViewModel = koinViewModel<NotesViewModel>()
            val dateHistory = notesViewModel.dateHistory
            val notesByDate = notesViewModel.notes.collectAsState()
                .value.groupBy { it?.createdAt?.date }
            val scrollState = rememberLazyListState()


            // Effect that fetches more dates once you get to the end of the list
            LaunchedEffect(scrollState){
                snapshotFlow {
                    scrollState.firstVisibleItemIndex +
                            scrollState.layoutInfo.visibleItemsInfo.size
                }.collect { visibleEndIndex ->
                    if (visibleEndIndex >= scrollState
                        .layoutInfo.totalItemsCount - 1) {
                        dateHistory.value.lastOrNull()?.let { lastDate ->
                            notesViewModel.generateDateHistory(lastDate)
                        }
                    }
                }
            }


            Column(
                modifier = Modifier.fillMaxSize().padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Row {
                    TextField("Search...", onValueChange = { /*TODO*/ })
                    Button(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Place, contentDescription = "Filters")
                    }
                }

                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
                    state = scrollState,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        itemsIndexed(dateHistory.value) { index, date ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .background(Color.Green)
                                    .height(96.dp)
                                    .border(2.dp, Color.Black)
                                    .clip(MaterialTheme.shapes.large)
                            ) {
                                Column {
                                    Text(date.toString())
                                    Text(index.toString())
                                    notesByDate[date]?.forEach { note ->
                                        Column {
                                            Text(note?.title ?: "")
                                            Text(note?.content ?:  "")
                                        }
                                    }


                                }

                        }
                    }


                }
                //TODO: Scroll to note on note screen
                /*

                //val navigateToNote: Note = ...

                LaunchedEffect(navigateToNote) {
                    navigateToNote?.run {
                        scrollState.animateScrollTo(indexOfNavigateToNote)
                    }

                }

                 */
            }
        }