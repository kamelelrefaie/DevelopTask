package com.example.developnetworktask.presentation.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.data.repository.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val dataStore: DataStoreManager) :
    ViewModel() {

    var state by mutableStateOf(LoginFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.PhoneChanged -> {
                state = state.copy(phone = event.phone)
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginEvent.Submit -> {
                submitData()
            }
        }
    }

    suspend fun getPrefData(context: Context): String {
        val userDataFlow: Flow<String> = dataStore.getFromDataStore()
        val userData = userDataFlow.first()

        return userData
    }

    private fun submitData() {
        val phoneResult = validatePhone(state.phone)
        val passwordResult = validatePassword(state.password)

        val hasError = listOf(
            phoneResult,
            passwordResult,

            ).any {
            !it.successful
        }

        if (hasError) {
            state = state.copy(
                phoneError = phoneResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            dataStore.save(state.phone)
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
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
}