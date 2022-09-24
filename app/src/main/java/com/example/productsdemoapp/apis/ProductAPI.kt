package com.example.productsdemoapp.apis

import retrofit2.http.POST
import retrofit2.http.Query

interface ProductAPI {

    @POST("products/producttitle?")
    suspend fun getProducts(
        @Query("lat") lat: String,
        @Query("lng") lng: String,
        @Query("page") page: String,
        @Query("title") title: String
    ): String
}