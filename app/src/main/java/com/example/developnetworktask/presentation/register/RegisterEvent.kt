package com.example.developnetworktask.presentation.register

import com.example.developnetworktask.presentation.login.LoginEvent

sealed class RegisterEvent{
    data class PhoneChanged(val phone: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    data class NameChanged(val name: String) : RegisterEvent()
    data class EmailChanged(val email: String) : RegisterEvent()
    object Submit: RegisterEvent()
}
