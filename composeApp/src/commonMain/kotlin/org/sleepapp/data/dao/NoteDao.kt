package org.sleepapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.sleepapp.data.entities.NoteEntity

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(item: NoteEntity): Long

    @Query("SELECT * FROM NoteEntity")
    fun getAllAsFlow(): Flow<List<NoteEntity>>

    @Update
    suspend fun update(item: NoteEntity): Int

    @Delete
    suspend fun delete(item: NoteEntity): Int

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun getById(id: Long): NoteEntity?

    @Query("SELECT * FROM NoteEntity WHERE createdAt = :createdAt")
    fun getByDateAsFlow(createdAt: String): Flow<List<NoteEntity>>
}