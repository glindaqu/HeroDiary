package com.example.herodiary.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.herodiary.state.LoginStates
import com.example.herodiary.viewModels.impl.LoginViewModel

@Composable
fun Login(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    when (uiState.value) {
        LoginStates.INPUT -> LoginInput(viewModel = viewModel)
        LoginStates.REQUEST -> Loading()
        LoginStates.SUCCESS -> viewModel.loadProfile()
        else -> Error(viewModel)
    }
}
