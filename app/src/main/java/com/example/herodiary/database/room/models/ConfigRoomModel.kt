package com.example.herodiary.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "config")
data class ConfigRoomModel(
    @PrimaryKey(autoGenerate = false)
    val key: String,
    @ColumnInfo(name = "value")
    val value: String? = null
)