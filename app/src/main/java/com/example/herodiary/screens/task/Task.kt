package com.example.herodiary.screens.task

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.herodiary.state.TaskScreenStates
import com.example.herodiary.viewModels.impl.TaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Task(extras: Bundle?) {
    val viewModel = ViewModelProvider(LocalContext.current as ComponentActivity)[TaskViewModel::class.java]
    val email = extras?.getString("email")!!
    val taskList by viewModel.getAllByCreatorEmail(email).collectAsState(initial = listOf())
    val systemTaskList by viewModel.getAllSystemTasks().collectAsState(initial = listOf())
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        TaskScreenStates.VIEW -> View(taskList = taskList.reversed(), systemTaskList = systemTaskList.reversed(),viewModel = viewModel, email = email)
        else -> { Create(email = email, viewModel = viewModel) }
    }
}