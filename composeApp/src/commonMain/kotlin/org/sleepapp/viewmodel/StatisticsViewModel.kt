package org.sleepapp.viewmodel

import androidx.lifecycle.ViewModel

class StatisticsViewModel(

) : ViewModel() {
    fun sayHello(name: String): String {
        return "Statistics $name"
    }


}