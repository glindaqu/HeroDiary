package com.example.herodiary.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.database.room.models.SystemTaskRoomModel
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.state.TaskScreenStates
import com.example.herodiary.ui.theme.blue1
import com.example.herodiary.viewModels.impl.TaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun View(taskList: List<TaskRoomModel>?, systemTaskList: List<SystemTaskRoomModel>?, viewModel: TaskViewModel, email: String) {
    Scaffold(
        containerColor = blue1,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.updateUiState(TaskScreenStates.CREATE) },
                containerColor = Color.White,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Text(
                    text = "Tasks",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp)
                        .padding(start = 10.dp)
                )
            }
            items(taskList ?: listOf()) { item ->
                TaskItem(item) { status, id ->
                    viewModel.updateStatus(status, id, email)
                }
            }
            item {
                Text(
                    text = "System Tasks",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp)
                        .padding(start = 10.dp)
                )
            }
            items(systemTaskList ?: listOf()) { item ->
                SystemTaskItem(item)
            }
        }
    }
}