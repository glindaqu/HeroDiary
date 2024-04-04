package com.example.herodiary.repository

import android.app.Application
import com.example.herodiary.database.ConfigKeys
import com.example.herodiary.database.room.HeroBase
import com.example.herodiary.database.room.dao.ConfigDao
import com.example.herodiary.database.room.models.ConfigRoomModel
import kotlinx.coroutines.flow.Flow

class ConfigRepository(application: Application) {
    private var configDao: ConfigDao
    init {
        configDao = HeroBase.getDatabase(application).getConfigDao()
    }
    suspend fun get(key: String): ConfigRoomModel? {
        return configDao.get(key)
    }
    suspend fun update(key: String, value: String) {
        configDao.updateConfig(key, value)
    }
    suspend fun insert(configRoomModel: ConfigRoomModel) {
        configDao.insert(configRoomModel)
    }

    fun getImageFlow(): Flow<Int> {
        return configDao.getImageFlow(ConfigKeys.IMAGE)
    }
}