package org.sleepapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.sleepapp.presentation.alarm.AlarmTab
import org.sleepapp.navigation.TabNavigationItem
import org.sleepapp.presentation.notes.NotesTab
import org.sleepapp.presentation.profile.ProfileTab
import org.sleepapp.presentation.statistics.StatisticsTab


@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            TabNavigator(tab = StatisticsTab){
                Scaffold(
                    content = { paddingValues ->
                        CurrentTab()
                    },
                    bottomBar = {
                        NavigationBar {
                            TabNavigationItem(AlarmTab)
                            TabNavigationItem(StatisticsTab)
                            TabNavigationItem(NotesTab)
                            TabNavigationItem(ProfileTab)
                        }
                    },
                )
            }

        }
    }
}

