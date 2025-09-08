package org.sleepapp.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDateTime
import org.sleepapp.data.dao.NotesDao
import org.sleepapp.data.model.Note

class NoteRepository(private val noteDao: NotesDao) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    val noteFlow: StateFlow<List<Note>> = noteDao
        .getAllNotesFlow()
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    suspend fun updateNote(note: Note) : Boolean {
        return noteDao.update(note)
    }

    suspend fun deleteNote(note: Note): Boolean{
       return noteDao.delete(note)
    }

    suspend fun insertNote(note: Note){
        noteDao.insert(note)
    }

    fun getNoteById(id: Long): Flow<Note?> {
        return noteDao.getNoteById(id)
    }

    suspend fun getNotesByDate(date: LocalDateTime): List<Note> {
        return noteDao.getNotesByDate(date)
    }




}