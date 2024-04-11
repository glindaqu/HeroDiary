package com.example.herodiary.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.herodiary.database.room.models.SystemTaskRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SystemTaskDao {
    @Insert
    suspend fun insert(task: SystemTaskRoomModel)
    @Delete
    suspend fun delete(task: SystemTaskRoomModel)
    @Query("SELECT * FROM systemTask")
    fun getAll(): Flow<List<SystemTaskRoomModel>>
    @Query("SELECT * FROM systemTask WHERE id = :id")
    suspend fun getById(id: Int): SystemTaskRoomModel?
    @Query("UPDATE systemTask SET done = :status WHERE id = :id")
    suspend fun updateStatus(status: Boolean, id: Int)
    @Query("SELECT * FROM systemTask WHERE done = :status AND type = :type")
    suspend fun getAllByType(status: Boolean = false, type: String): List<SystemTaskRoomModel>?
}