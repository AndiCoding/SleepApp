package org.sleepapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    ALARM("AlarmScreen", "Alarm", Icons.Default.Add, "Alarm"),
    //ACTIVEALARM("ActiveAlarmScreen", "ActiveAlarm", Icons.Default.Add, "ActiveAlarm"),
    STATISTICS("StatisticsScreen", "Statistics", Icons.Default.Add, "Statistics"),
    NOTES("NotesScreen", "Notes", Icons.Default.Add, "Notes"),
    PROFILE("ProfileScreen", "Profile", Icons.Default.Add, "Profile")
}