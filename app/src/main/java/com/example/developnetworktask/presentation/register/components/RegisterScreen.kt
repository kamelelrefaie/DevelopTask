import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.developnetworktask.R
import com.example.developnetworktask.presentation.login.LoginEvent
import com.example.developnetworktask.presentation.login.LoginScreenViewModel
import com.example.developnetworktask.presentation.navigation.navgraph.AuthScreen
import com.example.developnetworktask.presentation.register.RegisterEvent
import com.example.developnetworktask.presentation.register.RegisterViewModel
import com.example.developnetworktask.ui.theme.DevelopNetworkTaskTheme

@Preview(showSystemUi = true)
@Composable
fun RegisterScreen(
    navController: NavController = rememberNavController(),
    viewModel: RegisterViewModel = hiltViewModel()

) {
    val state = viewModel.state
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()


    val passwordVisibility = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                LoginScreenViewModel.ValidationEvent.Success -> {
                    navController.navigate(route = AuthScreen.Login.route)
                    Toast.makeText(context, "Registered successfully", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    DevelopNetworkTaskTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFE8EDFA)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(R.drawable.dv_icon),
                        contentDescription = "Register Image",
                        modifier = Modifier
                            .width(250.dp)
                            .height(250.dp)
                            .background(Black),
                        contentScale = ContentScale.Fit

                    )
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = -60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .offset(y = -20.dp)
                                .width(290.dp),
                            shape = RoundedCornerShape(25.dp),

                            ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(30.dp))
                                    .background(White)
                                    .padding(it),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {


                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    OutlinedTextField(
                                        value = state.name,
                                        onValueChange = { viewModel.onEvent(RegisterEvent.NameChanged(it)) },
                                        label = { Text(text = "Name", color = Black) },
                                        placeholder = { Text(text = "Name", color = Black) },
                                        singleLine = true,
                                        isError = state.nameError != null,
                                        modifier = Modifier.fillMaxWidth(0.8f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            unfocusedBorderColor = Color(0xFFC9C9C9),
                                            focusedBorderColor = Color(0xFF00BCD4),

                                            cursorColor = Color(0xFF00BCD4),
                                            focusedLabelColor = Color(0xFF00BCD4)
                                        )
                                    )
                                    if (state.nameError != null) {
                                        androidx.compose.material3.Text(
                                            text = state.nameError,
                                            color = MaterialTheme.colorScheme.error,

                                            )
                                    }
                                    OutlinedTextField(
                                        value = state.email,
                                        onValueChange = { viewModel.onEvent(RegisterEvent.EmailChanged(it)) },
                                        label = { Text(text = "Email Address", color = Black) },
                                        placeholder = {
                                            Text(
                                                text = "Email Address",
                                                color = Black
                                            )
                                        },
                                        singleLine = true,
                                        isError = state.emailError != null,
                                        modifier = Modifier.fillMaxWidth(0.8f),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            unfocusedBorderColor = Color(0xFFC9C9C9),
                                            focusedBorderColor = Color(0xFF00BCD4),

                                            cursorColor = Color(0xFF00BCD4),
                                            focusedLabelColor = Color(0xFF00BCD4)
                                        )
                                    )
                                    if (state.emailError != null) {
                                        androidx.compose.material3.Text(
                                            text = state.emailError,
                                            color = MaterialTheme.colorScheme.error,

                                            )
                                    }
                                    OutlinedTextField(
                                        value = state.phone,
                                        onValueChange = { viewModel.onEvent(RegisterEvent.PhoneChanged(it)) },
                                        label = { Text(text = "Phone Number", color = Black) },
                                        placeholder = {
                                            Text(
                                                text = "Phone Number",
                                                color = Black
                                            )
                                        },
                                        singleLine = true,
                                        isError = state.phoneError != null,
                                        modifier = Modifier.fillMaxWidth(0.8f),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            unfocusedBorderColor = Color(0xFFC9C9C9),
                                            focusedBorderColor = Color(0xFF00BCD4),

                                            cursorColor = Color(0xFF00BCD4),
                                            focusedLabelColor = Color(0xFF00BCD4)
                                        )
                                    )
                                    if (state.phoneError != null) {
                                        androidx.compose.material3.Text(
                                            text = state.phoneError,
                                            color = MaterialTheme.colorScheme.error,

                                            )
                                    }
                                    OutlinedTextField(
                                        value = state.password,
                                        onValueChange = { viewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
                                        label = { Text(text = "Password", color = Black) },
                                        placeholder = { Text(text = "Password", color = Black) },
                                        singleLine = true,
                                        isError = state.passwordError != null,
                                        modifier = Modifier.fillMaxWidth(0.8f),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            unfocusedBorderColor = Color(0xFFC9C9C9),
                                            focusedBorderColor = Color(0xFF00BCD4),

                                            cursorColor = Color(0xFF00BCD4),
                                            focusedLabelColor = Color(0xFF00BCD4)
                                        ),
                                        trailingIcon = {
                                            IconButton(
                                                onClick = {
                                                    passwordVisibility.value =
                                                        !passwordVisibility.value
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Rounded.Lock,
                                                    contentDescription = "Password Eye",
                                                    tint = if (passwordVisibility.value) Cyan else Color.Gray
                                                )
                                            }
                                        },
                                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                                        else PasswordVisualTransformation()
                                    )

                                    if (state.passwordError != null) {
                                        androidx.compose.material3.Text(
                                            text = state.passwordError,
                                            color = MaterialTheme.colorScheme.error,

                                            )
                                    }

                                    Spacer(modifier = Modifier.padding(10.dp))

                                    androidx.compose.material3.Button(
                                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent
                                        ),
                                        contentPadding = PaddingValues(),
                                        onClick = {
                                            viewModel.onEvent(RegisterEvent.Submit)

                                        },
                                        modifier = Modifier.height(48.dp),
                                        shape = RoundedCornerShape(40.dp),
                                        elevation = androidx.compose.material3.ButtonDefaults.buttonElevation(
                                            defaultElevation = 10.dp,
                                            pressedElevation = 5.dp
                                        )
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .background(
                                                    Brush.linearGradient(
                                                        listOf(
                                                            Color(0xFF2196F3),
                                                            Color(0xFF03A9F4),
                                                            Color(0xFF00BCD4)
                                                        )
                                                    )
                                                )
                                                .padding(horizontal = 32.dp, vertical = 8.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            androidx.compose.material3.Text(
                                                text = "Register",
                                                color = Color.White
                                            )
                                        }
                                    }


                                }
                            }
                        }
                    }

                }
            }
        }
    }
}