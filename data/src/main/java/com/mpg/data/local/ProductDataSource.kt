package com.mpg.data.local

import com.mpg.data.dto.ProductData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDataSource @Inject constructor(private val productDao: ProductDao) {
    suspend fun upsertProduct(productData: ProductData) {
        return productDao.upsertProduct(productData)
    }

    suspend fun deleteProduct(productData: ProductData) {
        return productDao.deleteProduct(productData)
    }

    fun getProducts(): Flow<List<ProductData>> {
        return productDao.getProducts()
    }

    suspend fun getProduct(id: Int): ProductData? {
        return productDao.getProduct(id)
    }
}