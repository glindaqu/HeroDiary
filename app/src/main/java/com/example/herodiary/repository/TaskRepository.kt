package com.example.herodiary.repository

import android.app.Application
import com.example.herodiary.database.room.HeroBase
import com.example.herodiary.database.room.dao.TaskDao
import com.example.herodiary.database.room.models.TaskRoomModel
import kotlinx.coroutines.flow.Flow

class TaskRepository(application: Application) {
    private val taskDao: TaskDao
    init {
        taskDao = HeroBase.getDatabase(application).getTaskDao()
    }
    val getAll: Flow<List<TaskRoomModel>> = taskDao.getAll()
    suspend fun getById(id: Int): TaskRoomModel? {
        return taskDao.getById(id)
    }
    suspend fun insert(task: TaskRoomModel) {
        taskDao.insert(task)
    }
    suspend fun delete(task: TaskRoomModel) {
        taskDao.delete(task)
    }
    fun getAllByCreatorEmail(email: String): Flow<List<TaskRoomModel>> {
        return taskDao.getAllByCreatorEmail(email)
    }
    suspend fun updateStatus(status: Boolean, targetTaskId: Int) {
        taskDao.updateStatus(status, targetTaskId)
    }

    fun getAllLaterThan(date: Long, email: String): Flow<List<TaskRoomModel>> {
        return taskDao.getAllLaterThan(date, email)
    }

    fun getAllByDate(date: Long, email: String): Flow<List<TaskRoomModel>> {
        return taskDao.getAllByDate(date, email)
    }
}