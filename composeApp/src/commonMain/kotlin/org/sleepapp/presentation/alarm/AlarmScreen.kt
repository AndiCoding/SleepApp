package org.sleepapp.presentation.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.viewmodel.AlarmViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.sleepapp.presentation.alarm.components.Clock


class AlarmScreen : Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            val alarmViewModel = koinViewModel<AlarmViewModel>()
            val navigator: Navigator = LocalNavigator.currentOrThrow


            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Clock()
            }
        }
    }
}

