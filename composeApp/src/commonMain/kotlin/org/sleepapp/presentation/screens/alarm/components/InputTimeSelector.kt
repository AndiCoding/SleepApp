package org.sleepapp.presentation.screens.alarm.components

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDateTime


@Composable
expect fun InputTimeSelector(
    onConfirm: (time: LocalDateTime) -> Unit,
    onDismiss: () -> Unit,
)