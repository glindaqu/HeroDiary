package com.example.herodiary.viewModels.impl

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.herodiary.R
import com.example.herodiary.database.room.models.ShopRoomModel
import com.example.herodiary.database.room.models.UserRoomModel
import com.example.herodiary.repository.ShopRepository
import com.example.herodiary.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    private val shopRepository = ShopRepository(application)
    private val userRepository = UserRepository(application)
    val all = MutableStateFlow(listOf(
        ShopRoomModel(1, 100, R.drawable.ava1),
        ShopRoomModel(2, 100, R.drawable.ava2),
    ))
    fun getAll(): Flow<List<ShopRoomModel>> {
        return shopRepository.getAll()
    }
    val currentUser = MutableStateFlow<UserRoomModel?>(null)

    fun initUser(email: String) {
        viewModelScope.launch {
            currentUser.value = userRepository.getByEmail(email)
        }
    }
}