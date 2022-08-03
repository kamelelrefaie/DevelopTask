package com.example.developnetworktask.presentation.register

data class RegisterFormState(
    val phone: String = "",
    val phoneError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)
