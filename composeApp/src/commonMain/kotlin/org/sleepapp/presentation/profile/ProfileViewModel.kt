package org.sleepapp.presentation.profile

import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    fun sayHello(name: String): String {
        return "Profile $name"
    }
}