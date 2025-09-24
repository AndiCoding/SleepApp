package org.sleepapp.presentation.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.sleepapp.presentation.screens.activealarm.ActiveAlarmScreen
import org.sleepapp.presentation.screens.alarm.AlarmScreen
import org.sleepapp.presentation.screens.notes.NotesScreen
import org.sleepapp.presentation.screens.profile.ProfileScreen
import org.sleepapp.presentation.screens.statistics.StatisticsScreen

@Serializable
object Alarm
@Serializable
object Statistics
@Serializable
object Notes
@Serializable
object Profile
@Serializable
data class ActiveAlarm(val alarmId: Long)

@Composable
fun SleepApp(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    val startDestination = Alarm
    var selectedDestination by remember {
        mutableIntStateOf(0)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isActiveAlarmVisible = navBackStackEntry?.destination?.route == ActiveAlarm::class.simpleName

    // Animate offset: 0.dp when visible, 80.dp down when hidden
    val navBarOffset by animateDpAsState(
        targetValue = if (isActiveAlarmVisible) 80.dp else 0.dp,
        label = "NavBarOffset"
    )


    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                modifier = Modifier.offset(y = navBarOffset),
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                NavigationBarItem(selected = selectedDestination == 0,
                    onClick = {
                        selectedDestination = 0
                        navController.navigate(Alarm)
                },
                    icon = {
                        Icon(imageVector =
                            if (selectedDestination == 0)
                                Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = {Text("Alarm")}
                )
                NavigationBarItem(selected = selectedDestination == 1,
                    onClick = {
                        selectedDestination = 1
                        navController.navigate(Statistics)
                    },
                    icon = {
                        Icon(imageVector =
                            if (selectedDestination == 1)
                                Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = {Text("Statistics")}
                )
                NavigationBarItem(selected = selectedDestination == 2,
                    onClick = {
                        selectedDestination = 2
                        navController.navigate(Notes)
                    },
                    icon = {
                        Icon(imageVector =
                            if (selectedDestination == 2)
                                Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = {Text("Notes")}
                )
                NavigationBarItem(selected = selectedDestination == 3,
                    onClick = {
                        selectedDestination = 3
                        navController.navigate(Profile)
                    },
                    icon = {
                        Icon(imageVector =
                            if (selectedDestination == 3)
                                Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = {Text("Profile")}
                )
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<Alarm> { AlarmScreen(navController) }
            composable<Statistics> { StatisticsScreen() }
            composable<Notes> { NotesScreen() }
            composable<Profile> { ProfileScreen() }
            composable<ActiveAlarm>(
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = tween(durationMillis = 1600) // 800ms for slower animation
                    )
                },
                popExitTransition = {
                    slideOutVertically(
                        targetOffsetY = { -it },
                        animationSpec = tween(durationMillis = 1600)
                    )
                }
            ) { backStackEntry ->
                val activeAlarm: ActiveAlarm = backStackEntry.toRoute()
                ActiveAlarmScreen(activeAlarm.alarmId, navController)
            }
        }
    }

}
