package org.sleepapp.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import cache.AlarmDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import org.sleepapp.data.model.Note
import org.sleepapp.data.util.getNow
import org.sleepapp.data.util.localDateTimeToString
import org.sleepapp.data.util.stringToLocalDateTime

class NotesDao(private val queries: AlarmDatabaseQueries) {
    fun getAllNotesFlow(): Flow<List<Note>> = queries
        .getAllNotes()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { dbNotes ->
            dbNotes.map { note ->
                Note(
                    id = note.id,
                    title = note.title,
                    content = note.content,
                    createdAt = stringToLocalDateTime( note.createdAt)
                )
            }

        }.flowOn(Dispatchers.IO)


    fun insert(note: Note): Long {
        val createdAtString = localDateTimeToString(getNow())
        queries.insertNote(
            title = note.title,
            content = note.content,
            createdAt = createdAtString
        )
        return queries.getLastCreatedNoteId().executeAsOne()
    }

    fun delete(note: Note): Boolean {
        try {
            queries.deleteNote(note.id)
            return true
        } catch (e: Exception){
            print("Error deleting note:\n ${e.message}")
            return false
        }
    }

    fun update(note: Note): Boolean{
        return try {
            queries.updateNote(
                id = note.id,
                title = note.title,
                content = note.content
            )
            true
        } catch (e: Exception){
            print("Error updating Note:\n ${e.message}")
            false
        }

    }

    fun getNoteById(id: Long): Flow<Note?> {
        return queries.getNoteById(id)
            .asFlow()
            .map { it.executeAsOneOrNull() }
            .map { selectedNote ->
                selectedNote?.let { selectedNote ->
                Note(
                    id = selectedNote.id,
                    title = selectedNote.title,
                    content = selectedNote.content,
                    createdAt = stringToLocalDateTime(selectedNote.createdAt)
                )
            }
            }.flowOn(Dispatchers.IO)
    }

    fun getNotesByDate(date: LocalDateTime): Flow<List<Note>> {
        val dateString = date.date.toString()
        return queries.getNotesByDate(dateString)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbNotes ->
                dbNotes.map { note ->
                    Note(
                        id = note.id,
                        title = note.title,
                        content = note.content,
                        createdAt = stringToLocalDateTime(note.createdAt)
                    )
                }
            }.flowOn(Dispatchers.IO)
    }
}