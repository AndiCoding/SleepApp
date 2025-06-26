package org.sleepapp.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen
import org.sleepapp.viewmodel.ProfileViewModel


class ProfileScreen: Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            val profileViewModel = koinViewModel<ProfileViewModel>()

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Profile Screen")
                Text(text = profileViewModel.sayHello("profile from view model"))

            }
        }
    }
}
