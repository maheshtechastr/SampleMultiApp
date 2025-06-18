package com.mpg.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mpg.data.dto.ProductData
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProduct(productData: ProductData)

    @Delete
    suspend fun deleteProduct(productData: ProductData)

    @Query("SELECT * FROM ProductData")
    fun getProducts(): Flow<List<ProductData>>

    @Query("SELECT * FROM ProductData WHERE id=:id")
    suspend fun getProduct(id: Int): ProductData?
}