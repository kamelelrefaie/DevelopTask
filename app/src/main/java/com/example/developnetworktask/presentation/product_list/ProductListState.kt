package com.example.developnetworktask.presentation.product_list

import com.example.developnetworktask.domain.model.ProductItem

data class ProductListState(
    val products: List<ProductItem> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)
