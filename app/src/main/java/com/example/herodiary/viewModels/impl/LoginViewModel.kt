package com.example.herodiary.viewModels.impl

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.herodiary.MainActivity
import com.example.herodiary.viewModels.api.ILoginViewModel
import com.google.firebase.auth.FirebaseAuth
import java.lang.ref.WeakReference

class LoginViewModel(app: Application) : AndroidViewModel(app), ILoginViewModel {
    lateinit var auth: FirebaseAuth
    private lateinit var context: WeakReference<Context>

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

    override fun login(password: String, email: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && context.get() != null) {
                    startMain(email)
                } else if (context.get() != null) {
                    Toast.makeText(context.get()!!, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun register(password: String, email: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && context.get() != null) {
                    startMain(email)
                } else if (context.get() != null) {
                    Toast.makeText(context.get()!!, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun startMain(email: String) {
        val intent = Intent(context.get()!!, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putString("email", email)
        intent.putExtras(bundle)
        context.get()!!.startActivity(intent)
        (context.get()!! as Activity).finish()
    }
}