package com.example.herodiary.screens.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.herodiary.Route
import com.example.herodiary.Routes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BottomBar(selected: Route, navHostController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) { systemUiController.setNavigationBarColor(Color.White) }
    NavigationBar(containerColor = Color.White, tonalElevation = 10.dp, modifier = Modifier.shadow(10.dp)) {
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
                label = { Text(it.title, fontWeight = if (selected == it) FontWeight.Bold else FontWeight.Normal) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color(0x19EEEEEE))
            )
        }
    }
}