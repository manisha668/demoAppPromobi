 package com.example.promobitdemoapp1.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promobitdemoapp1.App
import com.example.promobitdemoapp1.db.dao.DataDao
import com.example.promobitdemoapp1.db.entity.DataEntity
import com.example.promobitdemoapp1.db.repository.DatabaseRepository
import com.example.promobitdemoapp1.network.api.NYTServiceCallInterface
import com.example.promobitdemoapp1.network.modal.Doc
import com.example.promobitdemoapp1.network.modal.NYTData
import com.example.promobitdemoapp1.utils.constants.AppConstants
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class NYTDataViewModel constructor(var retrofit: Retrofit, var dao: DataDao) : ViewModel() {
    var databaseRepository = DatabaseRepository(dao)
    companion object{
        var doctData : List<Doc>? = null
        var copyRightData = ""
    }
    var nytLiveData = MutableLiveData<List<Doc>>()
    var errorLiveData = MutableLiveData<String>()

    fun getNYTData(month : Int, year : Int) {
        if (databaseRepository.ifDataAvailable(copyRightData)) {
            databaseRepository.fetchData(copyRightData).subscribe(
                { data ->
                    val gson = Gson()
                    val data1 = gson.fromJson<NYTData>(data.data, NYTData::class.java)
                    nytLiveData.postValue(data1.response.docs)
                    Log.d("NYTDataViewModel","data from db : $data")
                },
                {}
            )
        } else {
            retrofit.create(NYTServiceCallInterface::class.java)
                .getArchieveData(year, month, AppConstants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    val gson = Gson()
                    val data1 = gson.toJson(data)
                    copyRightData = data.copyright

                    nytLiveData.postValue(data.response.docs)
                    val dataEntity = DataEntity(data.copyright, data1)
                    databaseRepository.saveData(dataEntity)
                },
                    { error ->
                        errorLiveData.postValue("error occurred while retrieving data, $error")
                    }
                )
        }
    }
}