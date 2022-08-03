package com.example.developnetworktask.presentation.login

data class LoginFormState(
    val phone: String = "",
    val phoneError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)
