package com.example.promobitdemoapp1.network.api

import android.util.Log
import com.example.promobitdemoapp1.network.modal.NYTData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTServiceCallInterface {
@GET("{year}/{month}.json")

fun getArchieveData(@Path("year") year:Int, @Path("month") month:Int, @Query("api-key")apiKey:String) : Observable<NYTData>
}