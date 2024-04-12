package com.example.herodiary.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.herodiary.R
import com.example.herodiary.screens.task.AgendaTaskItem
import com.example.herodiary.viewModels.impl.ProfileViewModel

@Composable
fun Profile(extras: Bundle?) {
    val viewModel = ViewModelProvider(LocalContext.current as ComponentActivity)[ProfileViewModel::class.java]
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        if (extras?.getString("email") != null)
            viewModel.initUser(extras.getString("email")!!)
        viewModel.attachContext(context)
    }
    val currentUser by viewModel.currentUser.collectAsState()
    val currentImage by viewModel.getImage().collectAsState(initial = null)
    val tasks by viewModel.getTasks(currentUser?.email ?: "").collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(currentImage ?: R.drawable.ic_launcher_background),
                contentDescription = "user_image",
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        clip = true
                        shape = CircleShape
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = if (currentUser == null) "null" else currentUser!!.email.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.Absolute.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Days online", textAlign = TextAlign.Center)
                        Text(currentUser?.totalDaysOnline.toString())
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Total XP", textAlign = TextAlign.Center)
                        Text(currentUser?.xp.toString())
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Money", textAlign = TextAlign.Center)
                        Text(currentUser?.money.toString() + "$")
                    }
                }
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
            Button(
                onClick = { viewModel.logout() },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = "Logout",
                    fontSize = 18.sp,
                )
            }
        }
    }
}