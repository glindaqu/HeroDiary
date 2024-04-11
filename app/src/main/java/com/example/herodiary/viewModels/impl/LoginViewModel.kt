package com.example.herodiary.viewModels.impl

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herodiary.MainActivity
import com.example.herodiary.database.ConfigKeys
import com.example.herodiary.database.room.models.ConfigRoomModel
import com.example.herodiary.database.room.models.UserRoomModel
import com.example.herodiary.repository.ConfigRepository
import com.example.herodiary.repository.UserRepository
import com.example.herodiary.shared.getDateDiff
import com.example.herodiary.shared.isOnline
import com.example.herodiary.state.LoginStates
import com.example.herodiary.viewModels.api.ILoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import java.util.Date

class LoginViewModel(app: Application) : AndroidViewModel(app), ILoginViewModel {
    lateinit var auth: FirebaseAuth
    private lateinit var context: WeakReference<Context>
    val uiState = MutableStateFlow(LoginStates.INPUT)
    private var email: String? = null
    private val userRepository = UserRepository(app)
    private val configRepository = ConfigRepository(app)

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

    override fun sendEmail(code: String) {
        TODO("Not yet implemented")
    }

    fun updateUiState(state: LoginStates) {
        uiState.value = state
    }

    override fun login(password: String, email: String) {
        if (!isOnline(context.get()!!)) {
            updateUiState(LoginStates.LOGIN_FAILED)
            return
        }
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
        if (!isOnline(context.get()!!)) {
            updateUiState(LoginStates.LOGIN_FAILED)
            return
        }
        uiState.value = LoginStates.REQUEST
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && context.get() != null) {
                    createUser(email)
                    uiState.value = LoginStates.SUCCESS
                    this.email = auth.currentUser!!.email
                } else if (context.get() != null) {
                    uiState.value = LoginStates.REGISTRATION_FAILED
                }
            }
    }

    fun loadProfile() {
        if (!isOnline(context.get()!!)) {
            updateUiState(LoginStates.LOGIN_FAILED)
            return
        }
        viewModelScope.launch {
            if (userRepository.getByEmail(email!!) == null)
                createUser(email!!)
            checkLastLogin()
            val intent = Intent(context.get()!!, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("email", email)
            intent.putExtras(bundle)
            context.get()!!.startActivity(intent)
            (context.get()!! as Activity).finish()
        }
    }

    private fun checkLastLogin() {
        viewModelScope.launch {
            val lastLogin = configRepository.get(ConfigKeys.LAST_LOGIN)
            if (lastLogin == null || getDateDiff(lastLogin.value!!.toLong()) != 0L) {
                userRepository.updateTotalDays(email!!)
            }
            configRepository.insert(ConfigRoomModel(ConfigKeys.LAST_LOGIN, Date().time.toString()))
        }
    }

    override fun createUser(email: String) {
        viewModelScope.launch {
            userRepository.insert(UserRoomModel(null, email, 0, 0, 1000))
        }
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setEmailToDB() {
        viewModelScope.launch {
            configRepository.insert(ConfigRoomModel(ConfigKeys.EMAIL, email))
        }
    }
    suspend fun getStoredEmail(): ConfigRoomModel? {
        return configRepository.get(ConfigKeys.EMAIL)
    }
}