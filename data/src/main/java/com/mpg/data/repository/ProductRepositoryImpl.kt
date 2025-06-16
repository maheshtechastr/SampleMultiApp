package com.mpg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mpg.data.mapper.toMap
import com.mpg.data.network.ProductPagingSource
import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl @Inject constructor(private val productPagingSource: ProductPagingSource): ProductRepository {
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
}