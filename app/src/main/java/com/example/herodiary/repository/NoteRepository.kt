package com.example.herodiary.repository

import android.app.Application
import com.example.herodiary.database.room.HeroBase
import com.example.herodiary.database.room.dao.NoteDao
import com.example.herodiary.database.room.models.NoteRoomModel
import kotlinx.coroutines.flow.Flow

class NoteRepository(application: Application) {
    private var noteDao: NoteDao
    init {
        noteDao = HeroBase.getDatabase(application).getNoteDao()
    }
    suspend fun insert(noteRoomModel: NoteRoomModel) {
        noteDao.insert(noteRoomModel)
    }
    suspend fun delete(noteRoomModel: NoteRoomModel) {
        noteDao.delete(noteRoomModel)
    }
    fun getAll(email: String): Flow<List<NoteRoomModel>> {
        return noteDao.getAll(email)
    }
    fun getAllByDate(email: String, date: Long): Flow<List<NoteRoomModel>> {
        return noteDao.getAllByDate(email, date)
    }

    suspend fun get(id: Int): NoteRoomModel? {
        return noteDao.get(id)
    }
}