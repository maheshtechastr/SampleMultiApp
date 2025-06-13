package com.mpg.models.product

data class Product(
    val description: String,
    val id: Int,
    val price: Double,
    val thumbnail: String,
    val title: String
)