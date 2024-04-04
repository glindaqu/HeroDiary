package com.example.herodiary.repository

import android.app.Application
import com.example.herodiary.database.room.HeroBase
import com.example.herodiary.database.room.dao.ShopDao
import com.example.herodiary.database.room.models.ShopRoomModel
import kotlinx.coroutines.flow.Flow

class ShopRepository(application: Application) {
    private var shopDao: ShopDao
    init {
        shopDao = HeroBase.getDatabase(application).getShopDao()
    }
    fun getAll(): Flow<List<ShopRoomModel>> {
        return shopDao.getAll()
    }
}