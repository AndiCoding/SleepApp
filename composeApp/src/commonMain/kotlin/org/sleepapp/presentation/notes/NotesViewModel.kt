package org.sleepapp.presentation.notes

import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {
    fun sayHello(name: String): String {
        return "Notes $name"
    }
}