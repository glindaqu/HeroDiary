package com.example.herodiary.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.herodiary.LoginActivity
import com.example.herodiary.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val alpha = remember { Animatable(0f) }
    val bgColor = MaterialTheme.colorScheme.background
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(bgColor)
        alpha.animateTo(1f, animationSpec = tween(1500))
        delay(1500)
        ContextCompat.startActivity(context, Intent(context, LoginActivity::class.java), null)
        (context as Activity).finish()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alpha(alpha.value),
        )
    }
}
