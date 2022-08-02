package com.example.developnetworktask.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.developnetworktask.presentation.product_list.components.ProductListScreen

@Composable
public fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController =navController , startDestination = Screen.Home.route){
     composable(route = Screen.Home.route){
         ProductListScreen()
     }
    }

}