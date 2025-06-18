package com.mpg.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mpg.data.dto.ProductData

@Database(entities = [ProductData::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao
}