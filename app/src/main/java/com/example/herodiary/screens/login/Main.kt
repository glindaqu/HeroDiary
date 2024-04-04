package com.example.herodiary.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.herodiary.viewModels.impl.LoginViewModel

@Composable
fun LoginInput(viewModel: LoginViewModel) {
    var showRegistration by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
    ) {
        Text(
            text = "HERO'S DIARY",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        when (showRegistration) {
            true -> Registration(viewModel = viewModel)
            else -> LoginField(viewModel = viewModel)
        }
        TextButton(onClick = { showRegistration = !showRegistration }) {
            Text(
                text = if (!showRegistration) "Not have an account?\nRegistration" else "Already registered?\nLogin",
                textAlign = TextAlign.Center
            )
        }
    }
}