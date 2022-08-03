package com.example.developnetworktask.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.developnetworktask.presentation.login.components.GradientButton
import com.example.developnetworktask.presentation.login.components.PasswordInputField
import com.example.developnetworktask.presentation.login.components.PhoneInputField
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(onClick: () -> Unit,viewModel: LoginScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        LaunchedEffect(key1 =context ){
            viewModel.validationEvents.collect{event->
                when(event){
                    LoginScreenViewModel.ValidationEvent.Success -> {
                        onClick()
                        Toast.makeText(context, "login successfully", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    )
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFE8EDFA),
                                Color(0xFF000000)
                            ),
                            radius = 415f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .offset(y = ((-20).dp)),
                    painter = painterResource(id = com.example.developnetworktask.R.drawable.dv_icon),
                    contentDescription = "Background Image"
                )
            }

            Card(
                modifier = Modifier
                    .offset(y = -20.dp)
                    .width(290.dp),
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .padding(top = 30.dp, bottom = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Product App",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    PhoneInputField()
                    PasswordInputField()
                }
            }

            GradientButton(
                modifier = Modifier.offset(y = (-50).dp),
                text = "Log in",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF00BCD4),
                        Color(0xFF03A9F4),
                        Color(0xFF2196F3)
                    )
                )
            ) {
        viewModel.onEvent(LoginEvent.Submit)
            }

        }
    }
}