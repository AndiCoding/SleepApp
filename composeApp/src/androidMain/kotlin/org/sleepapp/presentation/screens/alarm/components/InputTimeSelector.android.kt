package org.sleepapp.presentation.screens.alarm.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun InputTimeSelector(
    onConfirm: (time: LocalDateTime) -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())


    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.hour,
        initialMinute = currentTime.minute,
        is24Hour = true,
    )

    var hasFocus by remember { mutableStateOf(false) }

    Column {
        TimeInput(
            state = timePickerState,
            modifier = Modifier.onFocusChanged { focusState ->
                if (!focusState.isFocused && hasFocus) {
                    val selectedDateTime = LocalDateTime(
                        currentTime.year,
                        currentTime.month,
                        currentTime.dayOfMonth,
                        timePickerState.hour,
                        timePickerState.minute
                    )
                    onConfirm(selectedDateTime)
                }
                hasFocus = focusState.isFocused
            }
        )
    }
}