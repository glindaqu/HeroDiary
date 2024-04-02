package com.example.herodiary.screens.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.herodiary.Route
import com.example.herodiary.Routes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BottomBar(selected: Route, navHostController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) { systemUiController.setNavigationBarColor(Color(0xFFf3edf7)) }
    NavigationBar(containerColor = Color(0xFFf3edf7)) {
        Routes.all.forEach {
            NavigationBarItem(
                selected = selected == it,
                onClick = {
                    navHostController.navigate(it.title) {
                        popUpTo(navHostController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                          },
                icon = {
                    Icon(
                        imageVector = if (selected == it) it.selectedIcon else it.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(it.title) }
            )
        }
    }
}