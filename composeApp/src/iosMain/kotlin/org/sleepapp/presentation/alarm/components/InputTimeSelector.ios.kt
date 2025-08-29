package org.sleepapp.presentation.alarm.components

import androidx.compose.runtime.Composable

@Composable
actual fun InputTimeSelector(
    onConfirm: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    onConfirm(10,10)
}