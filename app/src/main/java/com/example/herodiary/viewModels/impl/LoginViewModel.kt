package com.example.herodiary.viewModels.impl

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.herodiary.MainActivity
import com.example.herodiary.viewModels.api.ILoginViewModel
import com.google.firebase.auth.FirebaseAuth
import java.lang.ref.WeakReference

class LoginViewModel(app: Application) : AndroidViewModel(app), ILoginViewModel {
    lateinit var auth: FirebaseAuth
    private lateinit var context: WeakReference<Context>

    fun attachContext(context: Context) {
        this.context = WeakReference(context)
    }

    override fun login(password: String, email: String) {
        TODO("Not yet implemented")
    }

    override fun register(password: String, email: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && context.get() != null) {
                    val intent = Intent(context.get()!!, MainActivity::class.java)
                    context.get()!!.startActivity(intent)
                    (context.get()!! as Activity).finish()
                } else if (context.get() != null) {
                    Toast.makeText(context.get()!!, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}