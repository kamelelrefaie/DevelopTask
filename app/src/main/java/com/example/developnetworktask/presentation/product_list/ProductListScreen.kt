package com.example.developnetworktask.presentation.product_list

import CustomDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.developnetworktask.presentation.product_list.components.ProductItemComposable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.developnetworktask.presentation.navigation.BottomBarScreen
import com.example.developnetworktask.presentation.navigation.navgraph.HomeNavGraph
import com.example.developnetworktask.presentation.product_list.components.ProductItemComposableStockOver

@Composable
fun ProductListScreen(viewModel: ProductListViewModel = hiltViewModel()) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state
    val showDialog = remember {
        mutableStateOf(false)
    }

    LazyColumn {
        item {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.onEvent(ProductListEvent.Refresh) }) {
            }
        }
        var location = 0
        items(state.products.size) { i ->
            Box(modifier = Modifier.clickable {
                showDialog.value = true
                location = i
            }) {
                val products = state.products

                if (state.products[i].stock > 50){
                  ProductItemComposableStockOver(productItem = products[i])  
                }else{
                ProductItemComposable(
                    productItem = products[i]
                )}

                if (showDialog.value) CustomDialog(
                    productItem = products[location],
                    setShowDialog = { showDialog.value = it })

                if (i < state.products.size  ) {
                    Divider(modifier = Modifier.padding(horizontal = 20.dp))
                }
            }

        }
        item() {
            if (true) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController(),navRoot:NavHostController) {

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ){ values ->
        Column(Modifier.padding(values)) {
            HomeNavGraph(navController = navController,navRoot)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = Color(0xFF2196F3) , contentColor = Color.White) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

