package com.example.herodiary.viewModels.api

import android.content.Context

interface ILoginViewModel {
    fun login(password: String, email: String)
    fun register(password: String, email: String)
    fun attachContext(context: Context)
}