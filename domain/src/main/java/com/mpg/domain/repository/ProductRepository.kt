package com.mpg.domain.repository

import androidx.paging.PagingData
import com.mpg.models.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProduct(page: Int, skip: Int): Flow<PagingData<Product>>

    suspend fun upsertProduct(productData: Product)

    suspend fun deleteProduct(productData: Product): Int?

    fun getProducts(): Flow<List<Product>>

    suspend fun getProduct(id: Int): Product?
}