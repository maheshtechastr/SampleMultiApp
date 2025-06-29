package com.mpg.domain.usecases

import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import javax.inject.Inject

class DeleteProduct @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product): Int {
        return productRepository.deleteProduct(product) ?: 0
    }
}