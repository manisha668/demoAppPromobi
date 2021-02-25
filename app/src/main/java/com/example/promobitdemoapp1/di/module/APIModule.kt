package com.example.promobitdemoapp1.di.module

import android.app.Application
import android.content.Context
import com.example.promobitdemoapp1.db.AppDatabase
import com.example.promobitdemoapp1.db.dao.DataDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class APIModule(private var baseURL: String, private var context: Context) {

    @Provides
    fun provideRetrofitInstance():Retrofit{
       return Retrofit.Builder()
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(baseURL)
           .build()
    }

    @Provides
    fun provideDatabaseInstance():DataDao{
     return   AppDatabase.getDatabaseInstance(context = context)!!.databaseDao()
    }

}