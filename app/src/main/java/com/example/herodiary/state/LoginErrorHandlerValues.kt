package com.example.herodiary.state

object PasswordStates {
    val OK = "Your password"
    val ERROR = "Password must be 6+ characters in length!"
}

object EmailStates {
    val OK = "Your email"
    val ERROR = "Email must contain @ and . characters!"
}

object PasswordAgainStates {
    val OK = "Password again"
    val ERROR = "Passwords must match!"
}