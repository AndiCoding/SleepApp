package org.sleepapp.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDateTime
import org.sleepapp.data.dao.NoteDao
import org.sleepapp.data.entities.AlarmEntity
import org.sleepapp.data.entities.NoteEntity
import org.sleepapp.data.model.Alarm
import org.sleepapp.data.model.Note
import org.sleepapp.data.util.DEFAULT_LOCAL_DATE_TIME
import org.sleepapp.data.util.LocalDateTimeConverter

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllAsFlow(): Flow<List<Note?>>{
        return noteDao.getAllAsFlow().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun updateNote(note: Note) : Int? {
        return note.toEntity()?.let { entity ->
            noteDao.update(entity)
        }
    }

    suspend fun deleteNote(note: Note): Int? {
       return note.toEntity()?.let { entity ->
           noteDao.delete(entity)
       }
    }

    suspend fun insertNote(note: Note): Long? {
        return note.toEntity()?.let { entity ->
            noteDao.insert(entity)
        }
    }

    suspend fun getById(id: Long): Note? {
        return noteDao.getById(id)?.toDomain()
    }

    fun getByDateAsFlow(date: LocalDateTime): Flow<List<Note>>? {
        return LocalDateTimeConverter.localDateTimeToString(date)?.let { dateString ->
            noteDao.getByDateAsFlow(dateString).map { entities ->
                entities.mapNotNull { it.toDomain() }
            }
        }

    }

    private fun NoteEntity.toDomain(): Note? {
        return Note(
            id = this.id,
            title = this.title,
            content = this.content,
            createdAt = LocalDateTimeConverter
                .fromString(this.createdAt)
                ?: DEFAULT_LOCAL_DATE_TIME,
        )

    }

    private fun Note.toEntity(): NoteEntity? {
        return NoteEntity(
            id = this.id,
            title = this.title,
            content = this.content,
            createdAt = LocalDateTimeConverter
                .localDateTimeToString(this.createdAt)
                ?: "",
        )

    }
}