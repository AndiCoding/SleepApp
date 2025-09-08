package org.sleepapp.data.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sleepapp.data.model.Note
import org.sleepapp.data.repository.NoteRepository
import org.sleepapp.data.util.getNow

class NoteStateHolder(
    private val noteRepository: NoteRepository
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _notes = noteRepository.noteFlow
    val notes: StateFlow<List<Note>> get() = _notes

    private val _notesByDate = MutableStateFlow<List<Note>>(emptyList())
    val notesByDate: StateFlow<List<Note>> get() = _notesByDate

    init {
        coroutineScope.launch{
            noteRepository.getNotesByDate(getNow()).collect {
                _notesByDate.value = it
            }
        }
    }

    fun clear(){
        coroutineScope.cancel()
    }
}