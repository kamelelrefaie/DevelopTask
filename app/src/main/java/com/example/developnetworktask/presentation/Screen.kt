package com.example.developnetworktask.presentation

sealed class Screen(val route: String) {
    object Home:Screen("home_screen")
//    object Login:Screen()
//    object Register: Screen()
//    object ForgetPassword: Screen()

}