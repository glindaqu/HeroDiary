package com.example.herodiary.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.herodiary.R
import com.example.herodiary.viewModels.impl.ProfileViewModel

@Composable
fun Profile(extras: Bundle?) {
    val viewModel = ViewModelProvider(LocalContext.current as ComponentActivity)[ProfileViewModel::class.java]
    val email by viewModel.email.collectAsState()
    LaunchedEffect(Unit) {
        if (extras?.getString("email") != null)
            viewModel.setEmail(extras.getString("email")!!)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "user_image",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                },
            contentScale = ContentScale.Fit
        )
        Text(
            text = email.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(100.dp))
        //user stats
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Profile_Test() {
    Profile(bundleOf())
}