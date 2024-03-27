package com.example.herodiary.viewModels.api

import com.google.firebase.auth.FirebaseAuth

interface ILoginViewModel {
    fun login(password: String, email: String)
    fun register(password: String, email: String)
}