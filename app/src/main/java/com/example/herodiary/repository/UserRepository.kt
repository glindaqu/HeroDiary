package com.example.herodiary.repository

import android.app.Application
import com.example.herodiary.database.room.HeroBase
import com.example.herodiary.database.room.dao.UserDao
import com.example.herodiary.database.room.models.UserRoomModel
import kotlinx.coroutines.flow.Flow


class UserRepository(app: Application) {
    private var userDao: UserDao

    init {
        userDao = HeroBase.getDatabase(app).getUserDao()
    }

    val getAll: Flow<List<UserRoomModel>> = userDao.getAll()

    suspend fun getByEmail(email: String): UserRoomModel? {
        return userDao.getByEmail(email)
    }

    suspend fun insert(user: UserRoomModel) {
        userDao.insert(user)
    }
}