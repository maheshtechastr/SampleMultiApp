package com.mpg.data.network

import com.mpg.data.dto.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ProductApi {
    @GET("/products")
    suspend fun getProducts(
        @Query("limit") page: Int,
        @Query("skip") skip: Int,
        @Query("select") selectedItems: String = "title,price,description,thumbnail",
    ): ProductResponse
}