package com.example.herodiary.viewModels.impl

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herodiary.R
import com.example.herodiary.database.ConfigKeys
import com.example.herodiary.database.room.models.ConfigRoomModel
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.repository.UserRepository
import com.example.herodiary.database.room.models.UserRoomModel
import com.example.herodiary.repository.ConfigRepository
import com.example.herodiary.repository.TaskRepository
import com.example.herodiary.viewModels.api.IProfileViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(app: Application) : AndroidViewModel(app), IProfileViewModel {
    val email = MutableStateFlow<String?>(null)
    private val userRepository = UserRepository(app)
    val currentUser = MutableStateFlow<UserRoomModel?>(null)
    private val configRepository = ConfigRepository(app)
    private val taskRepository = TaskRepository(app)

    override fun setEmail(email: String) {
        this.email.value = email
    }

    fun updateImage(drawable: Int) {
        viewModelScope.launch {
            configRepository.insert(ConfigRoomModel(ConfigKeys.IMAGE, drawable.toString()))
        }
    }

    fun getImage(): Flow<Int> {
        return configRepository.getImageFlow()
    }

    override fun initUser(email: String) {
        viewModelScope.launch {
            currentUser.value = userRepository.getByEmail(email)
        }
    }

    fun getTasks(email: String): Flow<List<TaskRoomModel>> {
        return taskRepository.getAllByCreatorEmail(email)
    }
}