package com.example.herodiary.screens.calendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.herodiary.screens.note.Item
import com.example.herodiary.screens.task.AgendaTaskItem
import com.example.herodiary.shared.getDateDiff
import com.example.herodiary.viewModels.impl.CalendarViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@Composable
fun DayInfo(date: Long, extras: Bundle?, backClick: () -> Unit) {
    val viewModel = ViewModelProvider(LocalContext.current as ComponentActivity)[CalendarViewModel::class.java]
    LaunchedEffect(Unit) {
        if (extras?.getString("email") != null)
            viewModel.initUser(extras.getString("email")!!)
    }
    val currentUser by viewModel.currentUser.collectAsState()
    viewModel.date = date
    val tasks by viewModel.getTasks(currentUser?.email ?: "").collectAsState(initial = listOf())
    val notes by viewModel.getNotes(currentUser?.email ?: "").collectAsState(initial = listOf())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { backClick() }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
                }
                Text(SimpleDateFormat("MMMM, d", Locale.ENGLISH).format(date))
            }
            Text(
                text = "Agenda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 30.dp, bottom = 15.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
        }
        items(tasks) {
            AgendaTaskItem(task = it)
        }
        item {
            Text(
                text = "Notes",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 30.dp, bottom = 15.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
        }
        items(notes.filter {
            Date(it.creationDate).day == Date(date).day
        }) {
            Item(note = it) {

            }
        }
    }
}