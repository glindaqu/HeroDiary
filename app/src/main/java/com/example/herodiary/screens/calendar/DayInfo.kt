package com.example.herodiary.screens.calendar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun DayInfo(date: Long, backClick: () -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row {
                IconButton(onClick = { backClick() }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
                }
                Text(SimpleDateFormat("MMMM, d").format(date))
            }
        }
    }
}