package com.example.herodiary.viewModels.impl

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herodiary.database.room.models.NoteRoomModel
import com.example.herodiary.repository.NoteRepository
import com.example.herodiary.state.NoteScreenStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository = NoteRepository(application)
    val email = MutableStateFlow<String?>(null)
    val uiState = MutableStateFlow(NoteScreenStates.VIEW_ALL)

    fun setEmail(email: String) {
        this.email.value = email
    }

    fun setUiState(state: NoteScreenStates) {
        uiState.value = state
    }

    fun getAll(): Flow<List<NoteRoomModel>> {
        return noteRepository.getAll(email.value ?: "")
    }

    fun insert(note: NoteRoomModel) {
        viewModelScope.launch {
            noteRepository.insert(note)
        }
    }

    fun getAllByDate(date: Long): Flow<List<NoteRoomModel>> {
        return noteRepository.getAllByDate(email.value ?: "", date)
    }

    suspend fun get(id: Int): NoteRoomModel? {
        return noteRepository.get(id)
    }
}