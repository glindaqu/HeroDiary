package com.example.herodiary.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "done")
    val done: Boolean? = false,
    @ColumnInfo(name = "deadline")
    val deadline: Long? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "creatorEmail")
    val creatorEmail: String? = null,
    @ColumnInfo(name = "reward")
    val reward: Int = 100,
    @ColumnInfo(name = "xp")
    val xp: Int = 100
)