package com.example.herodiary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.herodiary.screens.Profile
import com.example.herodiary.screens.components.BottomBar
import com.example.herodiary.ui.theme.HeroDiaryTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var selected by remember { mutableStateOf(Routes.PROFILE) }
            HeroDiaryTheme {
                Scaffold(
                    bottomBar = { BottomBar(selected = selected, navHostController = navController) }
                ) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        NavHost(navController = navController, startDestination = Routes.PROFILE.title) {
                            composable(Routes.PROFILE.title) { Profile(intent.extras); selected = Routes.PROFILE }
//                            composable("Calendar") {  }
//                            composable("Notes") {  }
//                            composable("Habits") {  }
                            composable(Routes.TASK.title) { selected = Routes.TASK }
//                            composable("Shop") {  }
                        }
                    }
                }
            }
        }
    }
}
