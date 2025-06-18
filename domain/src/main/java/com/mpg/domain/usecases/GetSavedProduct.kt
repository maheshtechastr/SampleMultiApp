package com.mpg.domain.usecases

import android.util.Log
import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import javax.inject.Inject

class GetSavedProduct @Inject constructor(private val productRepository: ProductRepository) {
    private val TAG = "GetSavedProduct"
    suspend operator fun invoke(id: Int): Product? {
        Log.i(TAG, "invoke: id==>$id")
        return productRepository.getProduct(id)
    }
}