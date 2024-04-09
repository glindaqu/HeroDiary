package com.example.herodiary.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.herodiary.database.room.models.NoteRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteRoomModel: NoteRoomModel)
    @Delete
    suspend fun delete(noteRoomModel: NoteRoomModel)
    @Query("SELECT * FROM notes WHERE creatorEmail = :email")
    fun getAll(email: String): Flow<List<NoteRoomModel>>
    @Query("SELECT * FROM notes WHERE creationDate = :date AND creatorEmail = :email")
    fun getAllByDate(email: String, date: Long): Flow<List<NoteRoomModel>>
    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun get(id: Int): NoteRoomModel?
}