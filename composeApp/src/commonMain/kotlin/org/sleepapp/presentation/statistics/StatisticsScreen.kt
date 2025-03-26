package org.sleepapp.presentation.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen


class StatisticsScreen : Screen {

    @Composable
    override fun Content() {
        MaterialTheme {
            val statisticsViewModel = koinViewModel<StatisticsViewModel>()

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Statistics Screen")
                Text(text = statisticsViewModel.sayHello("statistics from view model"))

            }
        }
    }
}

