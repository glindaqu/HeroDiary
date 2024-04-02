package com.example.herodiary.viewModels.api

import android.content.Context

interface ILoginViewModel {
    fun login(password: String, email: String)
    fun register(password: String, email: String)
    fun attachContext(context: Context)
    fun validatePassword(password: String): Boolean
    fun validateEmail(email: String): Boolean
    fun comparePasswords(pass1: String, pass2: String): Boolean
    fun sendEmail(code: String)
    fun createUser(email: String)
}