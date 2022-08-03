package com.example.developnetworktask.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.presentation.login.LoginFormState
import com.example.developnetworktask.presentation.login.LoginScreenViewModel
import com.example.developnetworktask.presentation.login.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(RegisterFormState())
    private val validationEventChannel = Channel<LoginScreenViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> state = state.copy(email = event.email)
            is RegisterEvent.NameChanged -> state = state.copy(name = event.name)
            is RegisterEvent.PasswordChanged -> state = state.copy(password = event.password)
            is RegisterEvent.PhoneChanged -> {
                state = state.copy(phone = event.phone)
            }
            RegisterEvent.Submit -> submitData()
        }
    }

    private fun submitData() {
        val phoneResult = validatePhone(state.phone)
        val passwordResult = validatePassword(state.password)
        val nameResult = validateName(state.name)
        val emailResult = validateEmail(state.email)

        val hasError = listOf(
            phoneResult,
            passwordResult,
            nameResult,
            emailResult

        ).any {
            !it.successful
        }

        if (hasError) {
            state = state.copy(
                phoneError = phoneResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                emailError = emailResult.errorMessage,
                nameError = nameResult.errorMessage,
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(LoginScreenViewModel.ValidationEvent.Success)
        }
    }


    private fun validatePhone(phone: String): ValidationResult {
        val pattern = Regex("^01[0125][0-9]{8}\$")
        return if (phone.isEmpty()) {
            ValidationResult(
                successful = false,
                errorMessage = "The phone number can't be empty"
            )
        } else if (!pattern.containsMatchIn(phone)) {
            ValidationResult(
                successful = false,
                errorMessage = "That's not a valid Phone number"
            )
        } else {
            ValidationResult(
                successful = true
            )
        }
    }

    private fun validatePassword(password: String): ValidationResult {

        return if (password.isEmpty()) {
            ValidationResult(
                successful = false,
                errorMessage = "Password can't be empty"
            )
        } else if (password.length < 8) {
            ValidationResult(
                successful = false,
                errorMessage = "Password must be at least 8 characters"
            )
        } else {
            ValidationResult(
                successful = true
            )
        }
    }

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    private fun validateEmail(email: String): ValidationResult {
        return if (email.isEmpty()) {
            ValidationResult(successful = false, errorMessage = "Email can't be empty")
        } else if (!email.matches(EMAIL_REGEX.toRegex())) {
            ValidationResult(
                successful = false,
                errorMessage = "Enter Valid Email"
            )
        } else {
            ValidationResult(
                successful = true
            )
        }
    }

    private fun validateName(name: String): ValidationResult {
        return if (name.isEmpty()) {
            ValidationResult(successful = false, errorMessage = "Name can't be empty")
        } else {
            ValidationResult(
                successful = true
            )
        }
    }
}