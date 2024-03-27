package com.example.herodiary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.herodiary.screens.SplashScreen
import com.example.herodiary.ui.theme.HeroDiaryTheme

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeroDiaryTheme {
                SplashScreen()
            }
        }
    }
}
