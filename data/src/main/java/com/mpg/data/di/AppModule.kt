//package com.mpg.data.di
//
//import com.mpg.data.network.ProductApi
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideApiInstance(): ProductApi {
//        return Retrofit
//            .Builder()
//            .baseUrl("https://dummyjson.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ProductApi::class.java)
//    }
//}