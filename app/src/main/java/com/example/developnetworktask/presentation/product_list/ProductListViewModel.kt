package com.example.developnetworktask.presentation.product_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.data.repository.DataStoreManager
import com.example.developnetworktask.domain.repository.ProductRepository
import com.example.developnetworktask.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val dataStore: DataStoreManager
) :
    ViewModel() {
    var state by mutableStateOf(ProductListState())
    var counter = 0

    init {
        getProductList(false)
    }

    fun onEvent(event: ProductListEvent) {
        when (event) {
            ProductListEvent.Refresh -> getProductList(true)
            else -> {
                counter = 0
                getProductList()
            }
        }
    }

    fun getProductList(fetchFromRemote: Boolean = false) {


        Log.e("kamle", "${counter}")
        viewModelScope.launch {
            repository.getProducts(
                fetchFromRemote = fetchFromRemote,
                dataStore.getFromDataStore().first(),
                counter
            )
                .collect { result ->
                    when (result) {
                        is Resource.Error -> TODO()
                        is Resource.Loading -> state = state.copy(isLoading = result.isLoading)
                        is Resource.Success -> {
                            result.data?.let { productList ->
                                state = state.copy(products = productList)
                            }
                        }
                    }
                }
        }
    }

}