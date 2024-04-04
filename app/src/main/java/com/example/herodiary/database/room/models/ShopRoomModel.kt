package com.example.herodiary.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop")
data class ShopRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "cost")
    val cost: Int = 100,
    @ColumnInfo(name = "drawable")
    val drawable: Int? = null
)