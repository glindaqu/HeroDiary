package com.example.herodiary.repository

import android.app.Application
import com.example.herodiary.database.room.HeroBase
import com.example.herodiary.database.room.dao.SystemTaskDao
import com.example.herodiary.database.room.models.SystemTaskRoomModel
import kotlinx.coroutines.flow.Flow

class SystemTaskRepository(application: Application) {
    private var systemTaskDao: SystemTaskDao

    init {
        systemTaskDao = HeroBase.getDatabase(application).getSystemTaskDao()
    }
    suspend fun insert(task: SystemTaskRoomModel) {
        systemTaskDao.insert(task)
    }
    suspend fun delete(task: SystemTaskRoomModel) {
        systemTaskDao.delete(task)
    }
    fun getAll(): Flow<List<SystemTaskRoomModel>> {
        return systemTaskDao.getAll()
    }
    suspend fun getById(id: Int): SystemTaskRoomModel? {
        return systemTaskDao.getById(id)
    }
    suspend fun updateStatus(status: Boolean, id: Int) {
        systemTaskDao.updateStatus(status, id)
    }
    suspend fun getAllByType(status: Boolean = false, type: String): List<SystemTaskRoomModel>? {
        return systemTaskDao.getAllByType(status, type)
    }
}