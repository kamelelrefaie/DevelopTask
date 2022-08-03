package com.example.developnetworktask.presentation.login

sealed class LoginEvent {

    data class PhoneChanged(val phone: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object Submit: LoginEvent()

}