package com.example.herodiary.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.herodiary.database.room.models.ConfigRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: ConfigRoomModel)
    @Delete
    suspend fun delete(config: ConfigRoomModel)
    @Query("SELECT * FROM config WHERE `key` = :key LIMIT 1")
    suspend fun get(key: String): ConfigRoomModel?
    @Query("UPDATE config SET value = :value WHERE `key` = :key")
    suspend fun updateConfig(key: String, value: String)
    @Query("SELECT * FROM config WHERE `key` = :key LIMIT 1")
    fun getImageFlow(key: String): Flow<Int>
}