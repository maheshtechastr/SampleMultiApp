package com.mpg.di

import android.app.Application
import androidx.room.Room
import com.mpg.data.AppConstants.BASE_URL
import com.mpg.data.AppConstants.PRODUCT_DB
import com.mpg.data.local.ProductDao
import com.mpg.data.local.ProductDatabase
import com.mpg.data.network.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInstance(): ProductApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductDatabase(
        application: Application
    ): ProductDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ProductDatabase::class.java,
            name = PRODUCT_DB
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(
        productDatabase: ProductDatabase
    ): ProductDao {
        return productDatabase.productDao()
    }
}