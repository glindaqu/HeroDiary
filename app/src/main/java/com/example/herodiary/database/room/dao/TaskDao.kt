package com.example.herodiary.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.herodiary.database.room.models.TaskRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: TaskRoomModel)
    @Delete
    suspend fun delete(task: TaskRoomModel)
    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<TaskRoomModel>>
    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getById(id: Int): TaskRoomModel?
    @Query("SELECT tasks.* FROM tasks JOIN users ON tasks.creatorEmail = users.email WHERE users.email = :email")
    fun getAllByCreatorEmail(email: String): Flow<List<TaskRoomModel>>
    @Query("UPDATE tasks SET done = :status WHERE id = :id")
    suspend fun updateStatus(status: Boolean, id: Int)
    @Query("SELECT * FROM tasks WHERE deadline >= :date AND creatorEmail = :email")
    fun getAllLaterThan(date: Long, email: String): Flow<List<TaskRoomModel>>
    @Query("SELECT * FROM tasks WHERE deadline = :date AND creatorEmail = :email")
    fun getAllByDate(date: Long, email: String): Flow<List<TaskRoomModel>>
}