package com.example.herodiary.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val colorScheme = lightColorScheme(
    primary = Color.Black,
    background = Color.White
)

@Composable
fun HeroDiaryTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    val bg = MaterialTheme.colorScheme.background
    val window = (view.context as Activity).window
    SideEffect {
        systemUiController.setSystemBarsColor(bg)
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}