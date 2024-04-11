package com.example.herodiary.viewModels.impl

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herodiary.database.room.models.SystemTaskRoomModel
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.repository.SystemTaskRepository
import com.example.herodiary.repository.TaskRepository
import com.example.herodiary.repository.UserRepository
import com.example.herodiary.state.TaskScreenStates
import com.example.herodiary.viewModels.api.ITaskViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application), ITaskViewModel {
    private val taskRepository = TaskRepository(application)
    private val userRepository = UserRepository(application)
    private val systemTaskRepository = SystemTaskRepository(application)
    val uiState = MutableStateFlow(TaskScreenStates.VIEW)

    override fun add(task: TaskRoomModel) {
        viewModelScope.launch {
            taskRepository.insert(task)
        }
    }

    override suspend fun getCreatorId(email: String): Int? {
        return userRepository.getByEmail(email)?.id
    }

    override fun getAllByCreatorEmail(email: String): Flow<List<TaskRoomModel>> {
        return taskRepository.getAllByCreatorEmail(email)
    }

    fun getAllSystemTasks(): Flow<List<SystemTaskRoomModel>> {
        return systemTaskRepository.getAll()
    }

    fun updateUiState(state: TaskScreenStates) {
        uiState.value = state
    }

    fun updateStatus(status: Boolean, targetTaskId: Int, email: String) {
        viewModelScope.launch {
            taskRepository.updateStatus(status, targetTaskId)
            userRepository.updateMoney(userRepository.getByEmail(email)?.money!! + taskRepository.getById(targetTaskId)?.reward!!, email)
            userRepository.updateXp(taskRepository.getById(targetTaskId)?.xp!!, email)
        }
    }
}