package com.example.developnetworktask.data.remote

import com.example.developnetworktask.data.remote.dto.RequestDto
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductApi {

    @GET("products")
    suspend fun getProducts(@Header("token") token: String): RequestDto

    companion object {
        const val BASE_URL = "https://dummyjson.com"
    }

}