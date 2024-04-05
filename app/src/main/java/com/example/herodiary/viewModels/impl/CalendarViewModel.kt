package com.example.herodiary.viewModels.impl

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.database.room.models.UserRoomModel
import com.example.herodiary.repository.TaskRepository
import com.example.herodiary.repository.UserRepository
import com.example.herodiary.viewModels.api.IProfileViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CalendarViewModel(application: Application) : AndroidViewModel(application),
    IProfileViewModel {
    private val taskRepository = TaskRepository(application)
    val currentUser = MutableStateFlow<UserRoomModel?>(null)
    private val userRepository = UserRepository(application)
    var date: Long = 0

    override fun setEmail(email: String) {
        TODO("Not yet implemented")
    }

    override fun initUser(email: String) {
        viewModelScope.launch {
            currentUser.value = userRepository.getByEmail(email)
        }
    }

    fun getTasks(email: String): Flow<List<TaskRoomModel>> {
        return taskRepository.getAllByDate(date, email)
    }
}