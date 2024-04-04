package com.example.herodiary.repository

import android.app.Application
import com.example.herodiary.database.room.HeroBase
import com.example.herodiary.database.room.dao.BoughtDao
import com.example.herodiary.database.room.models.BoughtRoomModel
import kotlinx.coroutines.flow.Flow

class BoughtRepository(application: Application) {
    private var boughtDao: BoughtDao
    init {
        boughtDao = HeroBase.getDatabase(application).getBoughtDao()
    }
    suspend fun insert(boughtRoomModel: BoughtRoomModel) {
        boughtDao.insert(boughtRoomModel)
    }
    fun getAll(email: String): Flow<List<Int>> {
        return boughtDao.getAll(email)
    }
}