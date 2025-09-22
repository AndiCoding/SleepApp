package org.sleepapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.sleepapp.presentation.navigation.SleepApp


@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            SleepApp()
        }
    }
}

