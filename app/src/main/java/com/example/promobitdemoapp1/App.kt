package com.example.promobitdemoapp1

import android.app.Application
import com.example.promobitdemoapp1.di.component.ApplicationComponent
import com.example.promobitdemoapp1.di.component.DaggerApplicationComponent
import com.example.promobitdemoapp1.di.module.APIModule

class  App : Application(){
    private val baseURL = "https://api.nytimes.com/svc/archive/v1/"
    private  val apiComponent by lazy {
        DaggerApplicationComponent.builder()
            .aPIModule(APIModule(baseURL))
            .build()
    }
       fun getAPIComponent():ApplicationComponent{
           return apiComponent
       }
}