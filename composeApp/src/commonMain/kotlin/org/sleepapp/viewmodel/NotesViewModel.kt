package org.sleepapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.sleepapp.data.model.Note
import org.sleepapp.data.repository.NoteRepository
import org.sleepapp.data.util.getNow

class NotesViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _notes = noteRepository.noteFlow
    val notes: StateFlow<List<Note>> get() = _notes


    private val _notesByDate = MutableStateFlow<List<Note>>(emptyList())
    val notesByDate: StateFlow<List<Note>> get() = _notesByDate
    fun getNotesByDate(localDateTime: LocalDateTime){
        viewModelScope.launch {
            _notesByDate.value = noteRepository.getNotesByDate(localDateTime)
        }
    }

    fun clearNotesByDate(){
        _notesByDate.value = emptyList()
    }


    fun emptyNote(): Note = Note(
            id = 0,
            title = "",
            content = "",
            createdAt = getNow(),
        )


    private val _currentNote = mutableStateOf(emptyNote())
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



    fun insertNote() {
        viewModelScope.launch {
            noteRepository.insertNote(currentNote.value)

            _currentNote.value = emptyNote()
        }
    }

    fun deleteNote(note:Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}