package com.example.herodiary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.herodiary.screens.Profile
import com.example.herodiary.ui.theme.HeroDiaryTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HeroDiaryTheme {
                Scaffold {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        NavHost(navController = navController, startDestination = "Profile") {
                            composable("Profile") { Profile(intent.extras) }
//                            composable("Calendar") {  }
//                            composable("Notes") {  }
//                            composable("Habits") {  }
//                            composable("Quests") {  }
//                            composable("Shop") {  }
                        }
                    }
                }
            }
        }
    }
}
