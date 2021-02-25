package com.example.promobitdemoapp1

import android.app.Application
import android.content.Context
import com.example.promobitdemoapp1.di.component.ApplicationComponent
import com.example.promobitdemoapp1.di.component.DaggerApplicationComponent
import com.example.promobitdemoapp1.di.module.APIModule
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication

class App() : Application() {
    private val baseURL = "https://api.nytimes.com/svc/archive/v1/"
        companion object {
            private lateinit var apiComponent: ApplicationComponent
        }
    override fun onCreate() {
        super.onCreate()
     apiComponent =  DaggerApplicationComponent.builder()
            .aPIModule(APIModule(baseURL, this))
            .build()
    }

    fun getAPIComponent():ApplicationComponent{
        return apiComponent
    }
}