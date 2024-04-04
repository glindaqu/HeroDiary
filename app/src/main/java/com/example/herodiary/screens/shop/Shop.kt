package com.example.herodiary.screens.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.herodiary.ui.theme.blue1
import com.example.herodiary.viewModels.impl.ShopViewModel

@Composable
fun Shop(extras: Bundle?) {
    val email = extras?.getString("email") ?: ""
    val viewModel = ViewModelProvider(LocalContext.current as ComponentActivity)[ShopViewModel::class.java]
    val shopItems by viewModel.all.collectAsState()
//    val currentUser by viewModel.currentUser.collectAsState()
    LaunchedEffect(Unit) { viewModel.initUser(email) }
    Surface(color = blue1, modifier = Modifier.fillMaxSize()) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            item {
                Text(
                    text = "Shop",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp)
                        .padding(start = 10.dp)
                )
            }
            items(shopItems) {
                ShopItem(shopRoomModel = it)
            }
        }
    }
}