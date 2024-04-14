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

// класс наследует соответсвующий интерфейс, а также системный класс AndroidViewModel для доступа к viewModel lifecycle
class TaskViewModel(application: Application) : AndroidViewModel(application), ITaskViewModel {
    // получаем репозитоий задач
    private val taskRepository = TaskRepository(application)
    // получаем репозиторий пользователей (для прикрепления задачи к порльзователю)
    private val userRepository = UserRepository(application)
    // получаем репозиторий системных задач
    private val systemTaskRepository = SystemTaskRepository(application)
    // сохраняем текущее состояние экрана (их два - просмотр и создание)
    val uiState = MutableStateFlow(TaskScreenStates.VIEW)

    // переопределяем функцию вставки созданной задачи из интерфейса
    override fun add(task: TaskRoomModel) {
        // запускаем корутину, так как обращение к бд может занять некоторое время и положить поток отрисовки UI
        viewModelScope.launch {
            // вызывыаем метод репозитория с соответствующим объектом
            taskRepository.insert(task)
        }
    }

    // функция получаения id пользователя по почте
    override suspend fun getCreatorId(email: String): Int? {
        return userRepository.getByEmail(email)?.id
    }

    // функция получения всех связанных с пользователем записей задач по его почте
    override fun getAllByCreatorEmail(email: String): Flow<List<TaskRoomModel>> {
        return taskRepository.getAllByCreatorEmail(email)
    }

    // получения списка системных задач
    fun getAllSystemTasks(): Flow<List<SystemTaskRoomModel>> {
        return systemTaskRepository.getAll()
    }

    // функция обновления UI состояния экрана
    fun updateUiState(state: TaskScreenStates) {
        uiState.value = state
    }

    // функция обновления статуса выбранной задачи
    fun updateStatus(status: Boolean, targetTaskId: Int, email: String) {
        // запускаем корутину, так как обращение к бд может занять некоторое время и положить поток отрисовки UI
        viewModelScope.launch {
            // обновляем статус задачи на "выполнено" (иной вариант невозможен, так как выполненую задачу невозможно
            // пометить как невыполненную
            taskRepository.updateStatus(status, targetTaskId)
            // обновляем количество валюты пользователя на основе того, значения, которое уже есть в бд
            // это значение может как увеличиваться, так и уменьшаться, поэтому нам нужно указать значения для именно перезаписи
            userRepository.updateMoney(userRepository.getByEmail(email)?.money!! + taskRepository.getById(targetTaskId)?.reward!!, email)
            //обновляем XP пользователя (XP может только расти, поэтому здесь указывается только прибавляемое значение
            userRepository.updateXp(taskRepository.getById(targetTaskId)?.xp!!, email)
        }
    }
}
