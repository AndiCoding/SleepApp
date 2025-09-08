package org.sleepapp.presentation.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen
import org.sleepapp.presentation.statistics.components.AlarmDetails
import org.sleepapp.presentation.statistics.components.SelectedDayNotes
import org.sleepapp.presentation.statistics.components.TimelineDisplay
import org.sleepapp.viewmodel.StatisticsViewModel


class StatisticsScreen : Screen {

    @Composable
    override fun Content() {
        MaterialTheme {
            val statisticsViewModel = koinViewModel<StatisticsViewModel>()

            Column(modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "2 day streak",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(24.dp))
                TimelineDisplay()

                Spacer(Modifier.height(24.dp))
                AlarmDetails()

                Spacer(Modifier.height(24.dp))
                Text("Notes",
                    style = MaterialTheme.typography.titleMedium)
                SelectedDayNotes()

            }
        }
    }
}
