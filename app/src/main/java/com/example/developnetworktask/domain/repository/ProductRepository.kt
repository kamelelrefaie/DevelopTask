package com.example.developnetworktask.domain.repository

import com.example.developnetworktask.domain.model.ProductItem
import com.example.developnetworktask.domain.util.Resource
import kotlinx.coroutines.flow.Flow


interface ProductRepository {
    suspend fun getProducts(fetchFromRemote:Boolean,token: String,counter:Int): Flow<Resource<List<ProductItem>>>
}