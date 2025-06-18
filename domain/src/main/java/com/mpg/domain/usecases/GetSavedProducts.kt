package com.mpg.domain.usecases

import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedProducts @Inject constructor(private val productRepository: ProductRepository) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.getProducts()
    }
}