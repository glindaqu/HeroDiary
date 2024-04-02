package com.example.herodiary.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.herodiary.R
import com.example.herodiary.state.LoginStates
import com.example.herodiary.viewModels.impl.LoginViewModel


@Composable
fun Error(viewModel: LoginViewModel) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(400.dp),
            iterations = LottieConstants.IterateForever
        )
        OutlinedButton(
            onClick = { viewModel.updateUiState(LoginStates.INPUT) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "Try again",
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}