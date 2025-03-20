package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel

class AlarmViewModel : ViewModel() {
    fun sayHello(name: String): String {
        return "Alarm $name"
    }
}