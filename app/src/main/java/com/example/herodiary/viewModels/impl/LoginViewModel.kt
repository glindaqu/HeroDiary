package com.example.herodiary.viewModels.impl

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import com.example.herodiary.MainActivity
import com.example.herodiary.state.LoginStates
import com.example.herodiary.viewModels.api.ILoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import java.lang.ref.WeakReference

class LoginViewModel(app: Application) : AndroidViewModel(app), ILoginViewModel {
    lateinit var auth: FirebaseAuth
    private lateinit var context: WeakReference<Context>
    val uiState = MutableStateFlow(LoginStates.INPUT)
    private var email: String? = null

    override fun attachContext(context: Context) {
        this.context = WeakReference(context)
    }

    override fun validatePassword(password: String): Boolean {
        return password.count() < 6
    }

    override fun validateEmail(email: String): Boolean {
        return !email.contains("@") || !email.contains(".")
    }

    override fun comparePasswords(pass1: String, pass2: String): Boolean {
        return pass1 != pass2
    }

    fun updateUiState(state: LoginStates) {
        uiState.value = state
    }

    override fun login(password: String, email: String) {
        uiState.value = LoginStates.REQUEST
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && context.get() != null) {
                    uiState.value = LoginStates.SUCCESS
                    this.email = auth.currentUser!!.email
                } else if (context.get() != null) {
                    uiState.value = LoginStates.LOGIN_FAILED
                }
            }
    }

    override fun register(password: String, email: String) {
        uiState.value = LoginStates.REQUEST
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && context.get() != null) {
                    uiState.value = LoginStates.SUCCESS
                    this.email = auth.currentUser!!.email
                } else if (context.get() != null) {
                    uiState.value = LoginStates.REGISTRATION_FAILED
                }
            }
    }

    fun loadProfile() {
        val intent = Intent(context.get()!!, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putString("email", email)
        intent.putExtras(bundle)
        context.get()!!.startActivity(intent)
        (context.get()!! as Activity).finish()
    }
}