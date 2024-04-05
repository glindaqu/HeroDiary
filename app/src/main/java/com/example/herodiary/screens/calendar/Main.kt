package com.example.herodiary.screens.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.herodiary.screens.components.calendar.EssentialCalendar
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CalendarScreen(navController: NavHostController) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Calendar",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .padding(start = 10.dp)
                )
                Text(
                    text = SimpleDateFormat("MMMM, d", Locale.ENGLISH).format(System.currentTimeMillis()),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 30.dp)
                        .padding(start = 10.dp)
                )
            }
            EssentialCalendar(navController)
        }
    }
}