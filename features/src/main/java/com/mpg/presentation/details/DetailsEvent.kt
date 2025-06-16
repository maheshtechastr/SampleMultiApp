package com.mpg.presentation.details

import com.mpg.models.product.Product


sealed class DetailsEvent {
    data class UpsertDeleteProduct(val product: Product) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}