package com.example.developnetworktask.presentation.product_list

sealed class ProductListEvent {
    object Refresh:ProductListEvent()
}
