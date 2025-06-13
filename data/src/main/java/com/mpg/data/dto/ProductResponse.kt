package com.mpg.data.dto

data class ProductResponse(
    val limit: Int,
    val products: List<ProductData>,
    val skip: Int,
    val total: Int
)