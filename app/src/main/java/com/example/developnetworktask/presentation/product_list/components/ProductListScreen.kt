package com.example.developnetworktask.presentation.product_list.components

import CustomDialog
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.developnetworktask.R
import com.example.developnetworktask.presentation.product_list.ProductListEvent
import com.example.developnetworktask.presentation.product_list.ProductListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

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
                val products = viewModel.state.products

                ProductItemComposable(
                    productItem = products[i]
                )

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