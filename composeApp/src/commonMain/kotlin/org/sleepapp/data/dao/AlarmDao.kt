package org.sleepapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.sleepapp.data.entities.AlarmEntity

@Dao
interface AlarmDao {
    @Insert
    suspend fun insert(item: AlarmEntity): Long

    @Query("SELECT * FROM AlarmEntity")
    fun getAllAsFlow(): Flow<List<AlarmEntity>>

    @Update
    suspend fun update(item: AlarmEntity): Int

    @Delete
    suspend fun delete(item: AlarmEntity): Int

    @Query("SELECT * FROM AlarmEntity WHERE id = :id")
    suspend fun getById(id: Long): AlarmEntity?


}
