package org.sleepapp.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.sleepapp.presentation.alarm.AlarmTab
import org.sleepapp.presentation.notes.NotesTab
import org.sleepapp.presentation.profile.ProfileTab
import org.sleepapp.presentation.statistics.StatisticsTab

@Composable
fun BottomNavigation(){
    TabNavigator(
        tab = AlarmTab
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                androidx.compose.material.BottomNavigation {
                    TabNavigationItem(AlarmTab)
                    TabNavigationItem(StatisticsTab)
                    TabNavigationItem(NotesTab)
                    TabNavigationItem(ProfileTab)
                }
            },
            content = { CurrentTab() },
        )
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator: TabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription =
                        tab.options.title
                )
            }
        },
        label = {
            Text(
                text = tab.options.title
            )
        }
    )
}