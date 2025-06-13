package com.mpg.data.mapper

import com.mpg.data.dto.ProductData
import com.mpg.models.product.Product

fun ProductData.toMap() = Product(
    description = description,
    id = id,
    price = price,
    thumbnail = thumbnail,
    title = title
)

//fun Product.toMap() = ProductData(
//    description = description,
//    id = id,
//    price = price,
//    thumbnail = thumbnail,
//    title = title
//)