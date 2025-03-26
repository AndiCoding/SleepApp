package org.sleepapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.sleepapp.data.model.AlarmItem

@Dao
interface AlarmDao {
    @Insert
    suspend fun insert(alarm: AlarmItem)

    @Update
    suspend fun update(alarm: AlarmItem)

    @Delete
    suspend fun delete(alarm: AlarmItem)

    @Query("SELECT * FROM AlarmItem")
    fun getAll(): Flow<List<AlarmItem>>

}