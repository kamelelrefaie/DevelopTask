package com.example.developnetworktask.presentation.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.developnetworktask.presentation.login.LoginScreenViewModel
import com.example.developnetworktask.presentation.product_list.HomeScreen
import com.example.developnetworktask.presentation.product_list.ProductListScreen

@Composable
fun RootNavigationGraph(navController: NavHostController,viewModel: LoginScreenViewModel= hiltViewModel()) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController,viewModel)

        composable(route = Graph.HOME) {
           HomeScreen(navRoot = navController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}