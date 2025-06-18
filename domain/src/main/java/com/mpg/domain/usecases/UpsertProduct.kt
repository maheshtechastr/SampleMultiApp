package com.mpg.domain.usecases

import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import javax.inject.Inject

class UpsertProduct @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(product: Product) {
        return productRepository.upsertProduct(product)
    }
}