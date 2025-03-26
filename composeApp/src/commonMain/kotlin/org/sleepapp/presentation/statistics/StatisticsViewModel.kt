package org.sleepapp.presentation.statistics

import androidx.lifecycle.ViewModel

class StatisticsViewModel : ViewModel() {
    fun sayHello(name: String): String {
        return "Statistics $name"
    }
}