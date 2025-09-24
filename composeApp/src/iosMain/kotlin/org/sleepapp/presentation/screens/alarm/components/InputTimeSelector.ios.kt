package org.sleepapp.presentation.screens.alarm.components

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalTime

@Composable
actual fun InputTimeSelector(
    onConfirm: (time: LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    onConfirm(LocalTime(23,0))
}