package com.example.herodiary.viewModels.api

import com.example.herodiary.database.room.models.TaskRoomModel
import kotlinx.coroutines.flow.Flow

interface ITaskViewModel {
    fun add(task: TaskRoomModel)
    suspend fun getCreatorId(email: String): Int?
    fun getAllByCreatorEmail(email: String): Flow<List<TaskRoomModel>>
}