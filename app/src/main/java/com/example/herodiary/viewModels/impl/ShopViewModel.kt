package com.example.herodiary.viewModels.impl

import android.app.Application
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herodiary.R
import com.example.herodiary.database.ConfigKeys
import com.example.herodiary.database.room.models.BoughtRoomModel
import com.example.herodiary.database.room.models.ConfigRoomModel
import com.example.herodiary.database.room.models.ShopRoomModel
import com.example.herodiary.database.room.models.UserRoomModel
import com.example.herodiary.repository.BoughtRepository
import com.example.herodiary.repository.ConfigRepository
import com.example.herodiary.repository.ShopRepository
import com.example.herodiary.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    private val shopRepository = ShopRepository(application)
    private val userRepository = UserRepository(application)
    private val configRepository = ConfigRepository(application)
    private val boughtRepository = BoughtRepository(application)
    val all = MutableStateFlow(listOf(
        ShopRoomModel(1, 100, R.drawable.ava1),
        ShopRoomModel(2, 100, R.drawable.ava2),
    ))
    private val currentUser = MutableStateFlow<UserRoomModel?>(null)
    private lateinit var context: WeakReference<Context>

    fun attachContext(context: Context) {
        this.context = WeakReference(context)
    }

    fun getAll(): Flow<List<ShopRoomModel>> {
        return shopRepository.getAll()
    }
    fun getBought(): Flow<List<Int>> {
        return boughtRepository.getAll(currentUser.value?.email ?: "")
    }

    fun buy(id: Int, cost: Int) {
        viewModelScope.launch {
            if (currentUser.value?.money!! >= cost) {
                boughtRepository.insert(BoughtRoomModel(null, currentUser.value?.email, id))
                userRepository.updateMoney(email = currentUser.value?.email!!, money = currentUser.value?.money!! - cost)
            } else if (context.get() != null) {
                Toast.makeText(context.get()!!, "Not enough money!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initUser(email: String) {
        viewModelScope.launch {
            currentUser.value = userRepository.getByEmail(email)
        }
    }

    fun updateImage(drawable: Int) {
        viewModelScope.launch {
            configRepository.insert(ConfigRoomModel(ConfigKeys.IMAGE, drawable.toString()))
        }
    }

    fun getImage(): Flow<Int> {
        return configRepository.getImageFlow()
    }
}