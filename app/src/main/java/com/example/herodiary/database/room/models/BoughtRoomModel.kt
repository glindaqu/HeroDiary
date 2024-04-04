package com.example.herodiary.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bought")
data class BoughtRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "email")
    val email: String? = null,
    @ColumnInfo(name = "itemId")
    val itemId: Int? = null
)