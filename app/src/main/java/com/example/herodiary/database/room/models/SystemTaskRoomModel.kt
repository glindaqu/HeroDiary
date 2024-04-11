package com.example.herodiary.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "systemTask")
data class SystemTaskRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo("title")
    val title: String? = null,
    @ColumnInfo("description")
    val desc: String? = null,
    @ColumnInfo("done")
    val done: Boolean = false,
    @ColumnInfo("reward")
    val reward: Int = 0,
    @ColumnInfo("xp")
    val xp: Int = 0,
    @ColumnInfo("type")
    val type: String,
    @ColumnInfo("condition")
    val condition: Int
)