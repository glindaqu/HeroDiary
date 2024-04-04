package com.example.herodiary.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.herodiary.database.room.models.BoughtRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BoughtDao {
    @Query("SELECT itemId FROM bought WHERE email = :email")
    fun getAll(email: String): Flow<List<Int>>
    @Insert
    suspend fun insert(boughtRoomModel: BoughtRoomModel)
}