package com.example.developnetworktask.data.remote

import com.example.developnetworktask.data.remote.dto.RequestDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products/?limit=50")
    suspend fun getProducts(@Header("token") token: String,@Query("skip") skip:Int): RequestDto

    companion object {
        const val BASE_URL = "https://dummyjson.com"
    }

}