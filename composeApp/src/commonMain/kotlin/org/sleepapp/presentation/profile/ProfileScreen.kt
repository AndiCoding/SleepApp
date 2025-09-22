package org.sleepapp.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.sleepapp.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(){
    val profileViewModel = koinViewModel<ProfileViewModel>()

    Column(modifier = Modifier.fillMaxSize())
    {
        Text(text = "Profile Screen")
        Text(text = profileViewModel.sayHello("profile from view model"))

    }
}
