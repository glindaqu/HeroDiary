package com.example.herodiary.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.herodiary.state.LoginStates
import com.example.herodiary.viewModels.impl.LoginViewModel

@Composable
fun Login(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        val storedEmail = viewModel.getStoredEmail()?.value
        if (storedEmail != null) {
            viewModel.updateUiState(LoginStates.REQUEST)
            viewModel.setEmail(storedEmail)
            viewModel.loadProfile()
        }
    }
    when (uiState.value) {
        LoginStates.INPUT -> LoginInput(viewModel = viewModel)
        LoginStates.REQUEST -> Loading()
        LoginStates.SUCCESS -> {
            viewModel.setEmailToDB()
            viewModel.loadProfile()
        }
        else -> Error(viewModel)
    }
}
