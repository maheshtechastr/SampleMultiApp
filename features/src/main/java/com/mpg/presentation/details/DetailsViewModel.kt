package com.mpg.presentation.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpg.domain.usecases.DeleteProduct
import com.mpg.domain.usecases.GetSavedProduct
import com.mpg.domain.usecases.UpsertProduct
import com.mpg.models.product.Product
import com.mpg.presentation.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedProductUseCase: GetSavedProduct,
    private val upsertProductUseCase: UpsertProduct,
    private val deleteProductUseCase: DeleteProduct
) : ViewModel() {
    private val TAG = "DetailsViewModel"
    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteProduct -> {
                viewModelScope.launch {
                    val product = getSavedProductUseCase(id = event.product.id)
                    Log.i(TAG, "onEvent: $product")
                    if (product != null) {
                        deleteProduct(product = event.product)
                    } else {
                        upsertProduct(product = event.product)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteProduct(product: Product) {
        deleteProductUseCase(product = product)
        sideEffect = UIComponent.Toast("Product deleted")
    }

    private suspend fun upsertProduct(product: Product) {
        upsertProductUseCase(product = product)
        sideEffect = UIComponent.Toast("Product Inserted")
    }

}