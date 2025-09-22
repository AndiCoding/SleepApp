package org.sleepapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import org.sleepapp.data.model.Note
import org.sleepapp.data.repository.NoteRepository
import org.sleepapp.data.util.getNow

class NotesViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note?>>(emptyList())
    val notes: StateFlow<List<Note?>> get() = _notes

    private val _notesByDate = MutableStateFlow<List<Note>>(emptyList())
    val notesByDate: StateFlow<List<Note>> get() = _notesByDate

    private val _currentNote = mutableStateOf(emptyNote())
    val currentNote get()= _currentNote

    private val _dateHistory = MutableStateFlow(
        generateDateHistory()
    )
    val dateHistory = _dateHistory

    fun getNotesByDate(localDateTime: LocalDateTime){
        viewModelScope.launch {
            noteRepository.getByDateAsFlow(localDateTime)?.let { flow ->
                flow.collect { notes ->
                    _notesByDate.value = notes
                }
            }
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

    fun insertNote(dateForNote: LocalDateTime) {
        _currentNote.value = _currentNote.value.copy(createdAt = dateForNote)
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



    fun generateDateHistory(localDate : LocalDate? = null): List<LocalDate> {
     return if(localDate === null)  {
         (0..40).map { offset -> getNow()
             .date.minus(DatePeriod(days = offset)) }
     } else {
         _dateHistory
         (0..40).map {
             offset -> localDate.minus(DatePeriod(days = offset))
         }
     }
    }


}