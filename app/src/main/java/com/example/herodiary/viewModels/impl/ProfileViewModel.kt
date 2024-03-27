package com.example.herodiary.viewModels.impl

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.herodiary.viewModels.api.IProfileViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel(app: Application) : AndroidViewModel(app), IProfileViewModel {
    val email = MutableStateFlow<String?>(null)

    override fun setEmail(email: String) {
        this.email.value = email
    }
}