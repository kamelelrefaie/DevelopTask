package com.example.developnetworktask.presentation.product_list

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
class ProductListViewModel @Inject constructor(private val repository: ProductRepository,private val dataStore: DataStoreManager) :
    ViewModel() {
    var state by mutableStateOf(ProductListState())

    init {
       getProductList(false)
    }
    fun onEvent(event: ProductListEvent) {
        when (event) {
            ProductListEvent.Refresh -> getProductList(true)
        }
    }

    fun getProductList(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            repository.getProducts(fetchFromRemote = fetchFromRemote, dataStore.getFromDataStore().first())
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