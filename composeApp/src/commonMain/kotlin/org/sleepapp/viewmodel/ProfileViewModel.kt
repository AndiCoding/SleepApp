package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    fun sayHello(name: String): String {
        return "Profile $name"
    }
}