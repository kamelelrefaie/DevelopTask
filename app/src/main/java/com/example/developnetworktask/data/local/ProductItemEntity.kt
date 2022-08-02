package com.example.developnetworktask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductItemEntity(
    val brand: String,
    val description: String,
    val image: String,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String,
    @PrimaryKey val id: Int? = null
)
