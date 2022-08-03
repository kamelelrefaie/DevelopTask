//package com.example.developnetworktask.presentation.register
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.TextUnit
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.developnetworktask.R
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RegisterPage(navController: NavController) {
//
//    val image = painterResource(id = R.drawable.bg_icon)
//
//    val nameValue = remember { mutableStateOf("") }
//    val emailValue = remember { mutableStateOf("") }
//    val phoneValue = remember { mutableStateOf("") }
//    val passwordValue = remember { mutableStateOf("") }
//    val confirmPasswordValue = remember { mutableStateOf("") }
//
//    val passwordVisibility = remember { mutableStateOf(false) }
//    val confirmPasswordVisibility = remember { mutableStateOf(false) }
//
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            Image(painter = image, contentDescription = "")
//        }
//
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.70f)
//                .clip(RoundedCornerShape(topLeft = 30.dp, topRight = 30.dp))
//                .background(whiteBackground)
//                .padding(10.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Sign Up", fontSize = TextUnit.Sp(30),
//                    style = TextStyle(
//                        fontWeight = FontWeight.Bold,
//                        letterSpacing = TextUnit.Companion.Sp(2)
//                    )
//                )
//                Spacer(modifier = Modifier.padding(20.dp))
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    OutlinedTextField(
//                        value = nameValue.value,
//                        onValueChange = { nameValue.value = it },
//                        label = { Text(text = "Name") },
//                        placeholder = { Text(text = "Name") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f)
//                    )
//
//                    OutlinedTextField(
//                        value = emailValue.value,
//                        onValueChange = { emailValue.value = it },
//                        label = { Text(text = "Email Address") },
//                        placeholder = { Text(text = "Email Address") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f)
//                    )
//
//                    OutlinedTextField(
//                        value = phoneValue.value,
//                        onValueChange = { phoneValue.value = it },
//                        label = { Text(text = "Phone Number") },
//                        placeholder = { Text(text = "Phone Number") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
//                    )
//
//                    OutlinedTextField(
//                        value = passwordValue.value,
//                        onValueChange = { passwordValue.value = it },
//                        label = { Text(text = "Password") },
//                        placeholder = { Text(text = "Password") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f),
//                        trailingIcon = {
//                            IconButton(onClick = {
//                                passwordVisibility.value = !passwordVisibility.value
//                            }) {
//                                Icon(
//                                    imageVector = vectorResource(id = R.drawable.password_eye),
//                                    tint = if (passwordVisibility.value) primaryColor else Color.Gray
//                                )
//                            }
//                        },
//                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
//                        else PasswordVisualTransformation()
//                    )
//
//                    OutlinedTextField(
//                        value = confirmPasswordValue.value,
//                        onValueChange = { confirmPasswordValue.value = it },
//                        label = { Text(text = "Confirm Password") },
//                        placeholder = { Text(text = "Confirm Password") },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(0.8f),
//                        trailingIcon = {
//                            IconButton(onClick = {
//                                confirmPasswordVisibility.value = !confirmPasswordVisibility.value
//                            }) {
//                                Icon(
//                                    imageVector = vectorResource(id = R.drawable.password_eye),
//                                    tint = if (confirmPasswordVisibility.value) primaryColor else Color.Gray
//                                )
//                            }
//                        },
//                        visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
//                        else PasswordVisualTransformation()
//                    )
//                    Spacer(modifier = Modifier.padding(10.dp))
//                    Button(onClick = { }, modifier = Modifier
//                        .fillMaxWidth(0.8f)
//                        .height(50.dp)) {
//                        Text(text = "Sign Up", fontSize = TextUnit.Companion.Sp(20))
//                    }
//                    Spacer(modifier = Modifier.padding(20.dp))
//                    Text(
//                        text = "Login Instead",
//                        modifier = Modifier.clickable(onClick = {
//                            navController.navigate("login_page"){
//                                popUpTo = navController.graph.startDestination
//                                launchSingleTop = true
//                            }
//                        })
//                    )
//                    Spacer(modifier = Modifier.padding(20.dp))
//
//                }
//
//            }
//        }
//    }
//}
//
//
//
//
