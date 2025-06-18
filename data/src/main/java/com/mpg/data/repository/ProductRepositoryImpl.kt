package com.mpg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mpg.data.local.ProductDataSource
import com.mpg.data.mapper.toMap
import com.mpg.data.network.ProductPagingSource
import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl @Inject constructor(
    private val productPagingSource: ProductPagingSource,
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override fun getProduct(
        page: Int,
        skip: Int
    ): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                productPagingSource
            }
        ).flow.map { pagingData ->
            pagingData.map { product ->
                product.toMap()
            }
        }
    }

    override suspend fun upsertProduct(productData: Product) {
        return productDataSource.upsertProduct(productData.toMap())
    }

    override suspend fun deleteProduct(productData: Product) {
        return productDataSource.deleteProduct(productData.toMap())
    }

    override fun getProducts(): Flow<List<Product>> {
        return productDataSource.getProducts().map { products ->
            products.map { it.toMap() }
        }
    }

    override suspend fun getProduct(id: Int): Product? {
        return productDataSource.getProduct(id)?.toMap()
    }
}