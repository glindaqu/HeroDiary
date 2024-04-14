package com.example.herodiary.viewModels.api

import com.example.herodiary.database.room.models.TaskRoomModel
import kotlinx.coroutines.flow.Flow

// интерфейс класса TaskViewModel
interface ITaskViewModel {
    // добавление задачи, принимает созданный экземпляр соответствующего класса
    fun add(task: TaskRoomModel)
    // асинхронная функция получаения id пользователя по его почте
    suspend fun getCreatorId(email: String): Int?
    // функция получения всех связанных с пользователем (возвращает Flow, который позволяет добиться
    // нужной степени реактивности интерфейса пользователя)
    fun getAllByCreatorEmail(email: String): Flow<List<TaskRoomModel>>
}
