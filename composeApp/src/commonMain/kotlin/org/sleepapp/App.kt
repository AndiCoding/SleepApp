package org.sleepapp

import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.sleepapp.presentation.navigation.BottomNavigation



@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            BottomNavigation()
        }
    }
}

