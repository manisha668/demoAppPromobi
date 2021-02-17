package com.example.promobitdemoapp1.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class APIModule(private var baseURL: String) {

    @Provides
    fun provideRetrofitInstance():Retrofit{
       return Retrofit.Builder()
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(baseURL)
           .build()
    }
}