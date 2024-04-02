package com.example.herodiary.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "email")
    val email: String? = null,
    @ColumnInfo(name = "xp")
    val xp: Int? = 0,
    @ColumnInfo(name = "totalDaysOnline")
    val totalDaysOnline: Int? = 0,
    @ColumnInfo(name = "money")
    val money: Int? = 0,
)
