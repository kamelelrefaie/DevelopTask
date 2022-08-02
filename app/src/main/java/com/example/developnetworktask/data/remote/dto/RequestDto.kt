package com.example.developnetworktask.data.remote.dto

data class RequestDto(
    val limit: Int,
    val products: List<ProductItemDto>,
    val skip: Int,
    val total: Int
)