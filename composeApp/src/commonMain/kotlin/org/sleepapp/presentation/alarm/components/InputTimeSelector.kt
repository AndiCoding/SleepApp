package org.sleepapp.presentation.alarm.components

import androidx.compose.runtime.Composable


@Composable
expect fun InputTimeSelector(
    onConfirm: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
)