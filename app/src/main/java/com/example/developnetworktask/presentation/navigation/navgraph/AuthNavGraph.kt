package com.example.developnetworktask.presentation.navigation.navgraph

import RegisterScreen
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.developnetworktask.presentation.login.LoginScreen
import com.example.developnetworktask.presentation.login.LoginScreenViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.authNavGraph(navController: NavHostController,viewModel: LoginScreenViewModel) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
          val context = LocalContext.current
           LaunchedEffect(key1 = context){
               if (viewModel.getPrefData(context) != "0")  navController.navigate(Graph.HOME)
           }
        LoginScreen(onClick = {navController.navigate(Graph.HOME)}, navController = navController)

        }
        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(navController)
        }

    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
}