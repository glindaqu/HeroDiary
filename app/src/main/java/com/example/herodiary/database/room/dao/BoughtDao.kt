package com.example.herodiary.database.room.dao

import androidx.room.Dao

@Dao
interface BoughtDao {
    suspend fun insert()
}