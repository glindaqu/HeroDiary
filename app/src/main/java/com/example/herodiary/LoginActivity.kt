package com.example.herodiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.herodiary.screens.login.Login
import com.example.herodiary.ui.theme.HeroDiaryTheme
import com.example.herodiary.ui.theme.blue1
import com.example.herodiary.viewModels.impl.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    private lateinit var viewModel: LoginViewModel
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.attachContext(this)
        viewModel.auth = FirebaseAuth.getInstance()
        setContent {
            HeroDiaryTheme {
                Surface(color = blue1) {
                    Login(viewModel)
                }
            }
        }
    }
}
