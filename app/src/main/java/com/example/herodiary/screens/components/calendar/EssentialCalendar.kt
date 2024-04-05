package com.example.herodiary.screens.components.calendar


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.herodiary.Routes
import com.example.herodiary.shared.dayOnly
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun EssentialCalendar(navController: NavHostController) {
    val weekdayTitles = listOf("mon", "tue", "wed", "thu", "fri", "sat", "sun")
    val currentDay = dayOnly.format(Date()).toInt()
    val month = LocalDate.now().month
    val dayOffset = LocalDate.of(LocalDate.now().year, month, 1).dayOfWeek.value
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .height(30.dp),
                columns = GridCells.Fixed(7),
            ) {
                items(weekdayTitles) {
                    EssentialCalendarText(it = it)
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .padding(bottom = 3.dp)
                    .height(0.5.dp)
                    .background(Color.Black)
            )
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(7),
                verticalItemSpacing = 10.dp,
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .height(300.dp)
            ) {
                items((1..<dayOffset).toList()) {
                    EssentialCalendarNumberPlaceholder()
                }
                items((1..month.length(true)).toList()) {
                    EssentialCalendarNumberItem(
                        text = it.toString(),
                        isSelected = it == currentDay,
                        clickable = {
                            navController.navigate(Routes.DAY_INFO.title + "/" + (SimpleDateFormat("M, d yyyy").parse("${month.value}, $it ${LocalDate.now().year}")!!).time.toString())
                            Log.d("date", Routes.DAY_INFO.title + "/" + (SimpleDateFormat("M, d yyyy").parse("${month.value}, $it ${LocalDate.now().year}")!!).time)
                        }
                    )
                }
            }
        }
    }
}