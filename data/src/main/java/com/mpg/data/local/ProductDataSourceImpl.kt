package com.mpg.data.local

import com.mpg.data.dto.ProductData
import com.mpg.data.repository.ProductDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private val productDao: ProductDao): ProductDataSource {
    override suspend fun upsertProduct(productData: ProductData) {
        return productDao.upsertProduct(productData)
    }

    override suspend fun deleteProduct(productData: ProductData): Int? {
        val rows = productDao.deleteProduct(productData)
        return if (rows > 0) productData.id else null
    }

    override fun getProducts(): Flow<List<ProductData>> {
        return productDao.getProducts()
    }

    override suspend fun getProduct(id: Int): ProductData? {
        return productDao.getProduct(id)
    }
}