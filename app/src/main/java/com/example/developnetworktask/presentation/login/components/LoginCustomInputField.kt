package com.example.developnetworktask.presentation.login.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.developnetworktask.presentation.login.LoginEvent
import com.example.developnetworktask.presentation.login.LoginFormState
import com.example.developnetworktask.presentation.login.LoginScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneInputField(
    modifier: Modifier = Modifier,


    viewModel: LoginScreenViewModel = hiltViewModel(),
    iconColor: MutableState<Color> = remember {
        mutableStateOf(Color(0xFFC9C9C9))
    },

    ) {
    val state = viewModel.state
    val focusRequester = FocusRequester()
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color(0xFFF48FB1),
        backgroundColor = Color(0xA1F48FB1)
    )
    val rippleColor = rememberRipple(color = Color(0xFFF48FB1))

    CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors,
        LocalIndication provides rippleColor
    ) {
        Column {
            OutlinedTextField(
                value = state.phone,
                onValueChange = { viewModel.onEvent(LoginEvent.PhoneChanged(it)) },
                modifier = modifier
                    .height(72.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        iconColor.value = if (it.isFocused) Color(0xFFF06292)
                        else Color(0xFFC9C9C9)
                    },
                label = { Text(text = "Phone") },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Phone, contentDescription = "Login")

                },
                isError = state.phoneError != null,
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(25.dp),
                singleLine = true,
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        unfocusedBorderColor = Color(0xFFC9C9C9),
                        focusedBorderColor = Color(0xFF00BCD4),

                        cursorColor = Color(0xFF00BCD4),
                        focusedLabelColor = Color(0xFF00BCD4)
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            if (state.phoneError != null) {
                Text(
                    text = state.phoneError,
                    color = MaterialTheme.colorScheme.error,

                    )
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
   viewModel: LoginScreenViewModel = hiltViewModel(),
    iconColor: MutableState<Color> = remember {
        mutableStateOf(Color(0xFFC9C9C9))
    },
    seePasswordToggle: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
) {
val state = viewModel.state
    val focusRequester = FocusRequester()
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color(0xFFF48FB1),
        backgroundColor = Color(0xA1F48FB1)
    )
    val rippleColor = rememberRipple(color = Color(0xFFF48FB1))

    CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors,
        LocalIndication provides rippleColor
    ) {
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
            modifier = modifier
                .height(72.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    iconColor.value = if (it.isFocused) Color(0xFFF06292)
                    else Color(0xFFC9C9C9)
                },
            label = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Lock,
                    contentDescription = "Password"
                )
            },
            isError = state.passwordError != null,
            visualTransformation =
            if (!seePasswordToggle.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                Icon(
                    imageVector = if (seePasswordToggle.value) Icons.Rounded.Lock
                    else Icons.Rounded.Lock,
                    contentDescription = "Trailing Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .clickable { seePasswordToggle.value = !seePasswordToggle.value }

                )
            },
            shape = RoundedCornerShape(25.dp),
            singleLine = true,
            colors = TextFieldDefaults
                .outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFFC9C9C9),
                    focusedBorderColor = Color(0xFF00BCD4),

                    cursorColor = Color(0xFF00BCD4),
                    focusedLabelColor = Color(0xFF00BCD4)
                ),
            keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Password,
            )
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error,
                )
        }

    }
}

