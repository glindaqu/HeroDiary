package com.example.herodiary.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo("title")
    val title: String? = null,
    @ColumnInfo("content")
    val content: String? = null,
    @ColumnInfo("creatorEmail")
    val creatorEmail: String? = null,
    @ColumnInfo("creationDate")
    val creationDate: Long = 0
)