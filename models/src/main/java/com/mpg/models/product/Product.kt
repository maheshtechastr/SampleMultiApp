package com.mpg.models.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val description: String,
    val id: Int,
    val price: Double,
    val thumbnail: String,
    val title: String
): Parcelable