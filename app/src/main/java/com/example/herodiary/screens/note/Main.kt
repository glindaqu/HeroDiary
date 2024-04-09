package com.example.herodiary.screens.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.herodiary.state.NoteScreenStates
import com.example.herodiary.viewModels.impl.NoteViewModel

@Composable
fun NoteScreen(extras: Bundle?, navHostController: NavHostController) {
    val viewModel = ViewModelProvider(LocalContext.current as ComponentActivity)[NoteViewModel::class.java]
    val uiState by viewModel.uiState.collectAsState()
    val email by viewModel.email.collectAsState()
    LaunchedEffect(Unit) {
        if (extras?.getString("email") != null)
            viewModel.setEmail(extras.getString("email")!!)
    }
    when (uiState) {
        NoteScreenStates.VIEW_ALL -> ViewAll(viewModel = viewModel, navHostController = navHostController)
        else -> Create(email = email ?: "", viewModel = viewModel)
    }
}