package com.mpg.presentation.bookmark

import com.mpg.models.product.Product


data class BookmarkState(
    val products: List<Product> = emptyList()
)