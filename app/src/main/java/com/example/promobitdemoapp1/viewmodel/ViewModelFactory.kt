package com.example.promobitdemoapp1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.promobitdemoapp1.App
import com.example.promobitdemoapp1.db.dao.DataDao
import com.example.promobitdemoapp1.db.repository.DatabaseRepository
import retrofit2.Retrofit
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val retrofit: Retrofit, private val dao:DataDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NYTDataViewModel::class.java)){
            return NYTDataViewModel(retrofit, dao) as T
        }
        else{
            throw UnsupportedOperationException("Unsupported viewModel type $modelClass")
        }
    }
}