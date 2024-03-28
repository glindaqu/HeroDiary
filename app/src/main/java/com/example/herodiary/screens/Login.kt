package com.example.herodiary.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.herodiary.screens.login.Loading
import com.example.herodiary.screens.login.LoginInput
import com.example.herodiary.state.LoginStates
import com.example.herodiary.viewModels.impl.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Login(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    val systemUiController = rememberSystemUiController()
    val bg = MaterialTheme.colorScheme.background
    LaunchedEffect(Unit) { systemUiController.setSystemBarsColor(bg) }
    when (uiState.value) {
        LoginStates.INPUT -> LoginInput(viewModel = viewModel)
        LoginStates.REQUEST -> Loading()
        LoginStates.SUCCESS -> viewModel.loadProfile()
        else -> com.example.herodiary.screens.login.Error(viewModel)
    }
}



