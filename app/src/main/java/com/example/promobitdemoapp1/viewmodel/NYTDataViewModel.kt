package com.example.promobitdemoapp1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promobitdemoapp1.network.api.NYTServiceCallInterface
import com.example.promobitdemoapp1.network.modal.Doc
import com.example.promobitdemoapp1.network.modal.NYTData
import com.example.promobitdemoapp1.utils.constants.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class NYTDataViewModel constructor(var retrofit: Retrofit) : ViewModel() {
    var nytLiveData = MutableLiveData<List<Doc>>()
    var errorLiveData = MutableLiveData<String>()

    fun getNYTData(month : Int, year : Int) {
        retrofit.create(NYTServiceCallInterface::class.java)
            .getArchieveData(year, month, AppConstants.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { nytLiveData.postValue(it.response.docs) },
                { error ->
                    errorLiveData.postValue("Error occurred while calling API $error")
                }
            ).dispose()
    }
}