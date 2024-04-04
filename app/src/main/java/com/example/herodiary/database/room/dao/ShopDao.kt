package com.example.herodiary.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.herodiary.database.room.models.ShopRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {
    @Insert
    suspend fun insert(shop: ShopRoomModel)
    @Delete
    suspend fun delete(shop: ShopRoomModel)
    @Query("SELECT * FROM shop")
    fun getAll(): Flow<List<ShopRoomModel>>
}