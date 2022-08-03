package com.example.developnetworktask.presentation.login

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)