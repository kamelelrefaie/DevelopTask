package com.example.developnetworktask.presentation.navigation.navgraph

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.developnetworktask.presentation.navigation.BottomBarScreen
import com.example.developnetworktask.presentation.product_list.ProductListScreen
import com.example.developnetworktask.presentation.settings.LogoutScreen

@Composable
fun HomeNavGraph(navController: NavHostController,navControllerRoot: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
           ProductListScreen()
        }

        composable(route = BottomBarScreen.Settings.route) {
           LogoutScreen(navController=navControllerRoot)
        }

    }
}



