package com.mpg.di

import com.mpg.data.network.ProductApi
import com.mpg.data.repository.ProductRepositoryImpl
import com.mpg.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

//    @Provides
//    @Singleton
//    fun bindProductRepository(productApi: ProductApi): ProductRepository {
//        return ProductRepositoryImpl(productApi)
//    }
}