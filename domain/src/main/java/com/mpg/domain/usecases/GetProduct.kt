package com.mpg.domain.usecases

import androidx.paging.PagingData
import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProduct @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(page: Int, skip: Int): Flow<PagingData<Product>> {
        return productRepository.getProduct(page = page, skip = skip)
    }
}