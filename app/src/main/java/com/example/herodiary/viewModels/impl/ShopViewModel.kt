package com.example.herodiary.viewModels.impl

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Email
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

    fun getAll(): Flow<List<ShopRoomModel>> {
        return shopRepository.getAll()
    }
    fun getBought(): Flow<List<Int>> {
        return boughtRepository.getAll(currentUser.value?.email ?: "")
    }

    fun buy(id: Int) {
        viewModelScope.launch {
            boughtRepository.insert(BoughtRoomModel(null, currentUser.value?.email, id))
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
}