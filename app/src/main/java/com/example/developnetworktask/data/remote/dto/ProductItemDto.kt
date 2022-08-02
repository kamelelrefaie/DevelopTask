package com.example.developnetworktask.data.remote.dto

data class ProductItemDto(
    val brand: String,
    val description: String,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)