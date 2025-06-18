package com.mpg.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    val description: String,
    @PrimaryKey
    val id: Int,
    val price: Double,
    val thumbnail: String,
    val title: String
)