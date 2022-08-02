package com.example.developnetworktask.data.mapper

import com.example.developnetworktask.data.local.ProductItemEntity
import com.example.developnetworktask.data.remote.dto.ProductItemDto
import com.example.developnetworktask.domain.model.ProductItem

fun ProductItemEntity.toProductItem() : ProductItem{
    return ProductItem(
        brand = brand,
        description = description,
        image = image,
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title
    )
}

fun ProductItemDto.toProductItem() : ProductItem {
    return ProductItem(
        brand = brand,
        description = description,
        image = images[0],
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title
    )
}

    fun ProductItemDto.toProductItemEntity() : ProductItemEntity{
        return ProductItemEntity(
            brand = brand,
            description = description,
            image = images[0],
            price = price,
            rating = rating,
            stock = stock,
            thumbnail = thumbnail,
            title = title
        )
}