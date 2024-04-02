package com.example.herodiary.database

class User {
    companion object Factory {
        fun create(): User = User()
    }

    var id: String? = null
    var email: String? = null
    var nickname: String? = null
    var xp: Int = 0
    var money: Int = 0
    var daysOnline: Int = 0
}
