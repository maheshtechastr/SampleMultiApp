package com.mpg.data.repository

import com.mpg.data.dto.ProductData
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    suspend fun upsertProduct(productData: ProductData)

    suspend fun deleteProduct(productData: ProductData): Int?

    fun getProducts(): Flow<List<ProductData>>

    suspend fun getProduct(id: Int): ProductData?
}