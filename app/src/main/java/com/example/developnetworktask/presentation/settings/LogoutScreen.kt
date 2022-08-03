package com.example.developnetworktask.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.developnetworktask.presentation.navigation.navgraph.Graph.ROOT
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LogoutScreen(viewModel:LogoutViewModel = hiltViewModel(),navController: NavHostController ){
    val scope = rememberCoroutineScope()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent ),
            contentPadding = PaddingValues(),
            onClick = {
                scope.launch {
                    viewModel.onEvent(LogoutEvent.Logout)
                    delay(1000)
                    navController.navigate(ROOT)
                }

                      },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp, pressedElevation = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF2196F3),
                                Color(0xFF03A9F4),
                                Color(0xFF2196F3)
                            )
                        )
                    )
                    .padding(horizontal = 32.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Logout",
                    color = Color.White
                )
            }
        }
    }
}