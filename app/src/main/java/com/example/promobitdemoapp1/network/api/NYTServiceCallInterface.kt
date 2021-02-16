package com.example.promobitdemoapp1.network.api

import com.example.promobitdemoapp1.network.modal.NYTData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NYTServiceCallInterface {
@GET("{year}/{month}.json?api-key=J6AcBenupmA5W5MqJDUe85ut8m77Hg0z")

fun getArchieveData(@Path("year") year:Int, @Path("month") month:Int) : Observable<NYTData>

companion object{
   private var BASE_URL = "https://api.nytimes.com/svc/archive/v1/"
    fun create():NYTServiceCallInterface{
       val retrofit = Retrofit.Builder()
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL).build()
        return retrofit.create(NYTServiceCallInterface::class.java)
    }
}
}