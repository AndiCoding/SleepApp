package org.sleepapp.presentation.alarm.components

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalTime


@Composable
expect fun InputTimeSelector(
    onConfirm: (time: LocalTime) -> Unit,
    onDismiss: () -> Unit,
)