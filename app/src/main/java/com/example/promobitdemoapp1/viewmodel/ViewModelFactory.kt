package com.example.promobitdemoapp1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val retrofit: Retrofit): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NYTDataViewModel::class.java)){
            return NYTDataViewModel(retrofit) as T
        }
        else{
            throw UnsupportedOperationException("Unsupported viewModel type $modelClass")
        }
    }
}