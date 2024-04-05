package com.example.herodiary.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.herodiary.database.room.models.UserRoomModel
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserRoomModel)
    @Delete
    suspend fun delete(user: UserRoomModel)
    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<UserRoomModel>>
    @Query("SELECT * FROM users WHERE `email` = :email")
    suspend fun getByEmail(email: String): UserRoomModel?
    @Query("UPDATE users SET money = :money WHERE email = :email")
    suspend fun updateMoney(money: Int, email: String)
    @Query("UPDATE users SET totalDaysOnline = totalDaysOnline + 1 WHERE email = :email")
    suspend fun updateTotalDays(email: String)
}