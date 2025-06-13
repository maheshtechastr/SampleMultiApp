package com.mpg.data.network

import com.mpg.data.dto.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query


//https://dummyjson.com/products
//https://dummyjson.com/products?limit=10&skip=20&select=title,price,description,thumbnail
interface ProductApi {
    @GET("/products")
    suspend fun getProducts(
        @Query("limit") page: Int,
        @Query("skip") skip: Int,
        @Query("select") selectedItems: String = "title,price,description,thumbnail",
    ): ProductResponse
}