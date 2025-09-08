package org.sleepapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.sleepapp.data.model.Note
import org.sleepapp.data.repository.NoteRepository
import org.sleepapp.data.state.NoteStateHolder
import org.sleepapp.data.util.getNow

class NotesViewModel(
    private val noteStateHolder: NoteStateHolder,
    private val noteRepository: NoteRepository
) : ViewModel() {
    override fun onCleared(){
        super.onCleared()
        noteStateHolder.clear()
    }
    val notes = noteStateHolder.notes
    val notesByDate = noteStateHolder.notesByDate

    private val _currentNote = mutableStateOf(Note(
        id = 0,
        title = "",
        content = "",
        createdAt = getNow(),
    ))
    val currentNote get()= _currentNote

    fun setTitle(title: String){
        _currentNote.value = _currentNote.value.copy(title = title)

    }

    fun setContent(content: String){
        _currentNote.value = _currentNote.value.copy(content = content)
    }

    fun updateNote(note: Note) {
     viewModelScope.launch {
         noteRepository.updateNote(note)
     }
    }

    fun getNotesByDate(){}

    fun insertNote() {
        viewModelScope.launch {
            noteRepository.insertNote(currentNote.value)

            _currentNote.value = Note(
                id = 0,
                title = "",
                content = "",
                createdAt = getNow()
            )
        }
    }

    fun deleteNote(note:Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}