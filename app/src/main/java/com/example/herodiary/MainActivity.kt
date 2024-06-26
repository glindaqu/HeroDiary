package com.example.herodiary

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.herodiary.notify.createNotificationChannel
import com.example.herodiary.screens.Profile
import com.example.herodiary.screens.calendar.CalendarScreen
import com.example.herodiary.screens.calendar.DayInfo
import com.example.herodiary.screens.shop.Shop
import com.example.herodiary.screens.task.Task
import com.example.herodiary.screens.components.BottomBar
import com.example.herodiary.screens.note.Edit
import com.example.herodiary.screens.note.NoteScreen
import com.example.herodiary.ui.theme.HeroDiaryTheme
import com.example.herodiary.ui.theme.blue1

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)
        setContent {
            val navController = rememberNavController()
            var selected by remember { mutableStateOf(Routes.PROFILE) }
            HeroDiaryTheme {
                Scaffold(
                    bottomBar = { BottomBar(selected = selected, navHostController = navController) }
                ) { paddingValues ->
                    Surface(color = blue1, modifier = Modifier.padding(paddingValues)) {
                        NavHost(navController = navController, startDestination = Routes.PROFILE.title) {
                            composable(Routes.PROFILE.title) { Profile(intent.extras); selected = Routes.PROFILE }
                            composable(Routes.CALENDAR.title) { CalendarScreen(navController); selected = Routes.CALENDAR }
                            composable(
                                route = Routes.DAY_INFO.title + "/{date}",
                                arguments = listOf(navArgument("date") { NavType.StringType } )
                            ) {
                                DayInfo(date = it.arguments?.getString("date")!!.toLong(), extras = intent.extras) {
                                    navController.navigateUp()
                                }
                            }
                            composable(Routes.NOTES.title) { NoteScreen(intent.extras, navController); selected = Routes.NOTES }
                            composable(
                                route = Routes.NOTES_EDIT.title + "/{id}",
                                arguments = listOf(navArgument("id") { NavType.StringType } )
                            ) {
                                Edit(clickedId = it.arguments?.getString("id")!!.toInt()) {
                                    navController.navigateUp()
                                }
                            }
                            composable(Routes.TASK.title) { Task(intent.extras); selected = Routes.TASK }
                            composable(Routes.SHOP.title) { Shop(intent.extras); selected = Routes.SHOP }
                        }
                    }
                }
            }
        }
    }
}
