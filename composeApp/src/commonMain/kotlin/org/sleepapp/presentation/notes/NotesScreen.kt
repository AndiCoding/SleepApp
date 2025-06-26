package org.sleepapp.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen
import org.sleepapp.viewmodel.NotesViewModel


class NotesScreen : Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            val notesViewModel = koinViewModel<NotesViewModel>()

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
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        items(count = 8) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .background(Color.Green)
                                    .height(96.dp)
                                    .border(2.dp, Color.Black)
                                    .clip(MaterialTheme.shapes.large)

                            ) {
                                Text("NOTE")
                        }
                            Spacer(modifier = Modifier.height(16.dp))

                    }


                }
            }
        }
    }
}