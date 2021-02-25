package com.example.promobitdemoapp1.di.component

import com.example.promobitdemoapp1.di.module.APIModule
import com.example.promobitdemoapp1.view.activities.MainActivity
import com.example.promobitdemoapp1.view.fragments.BaseFragment
import com.example.promobitdemoapp1.viewmodel.NYTDataViewModel
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = arrayOf(APIModule::class))
interface ApplicationComponent  {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: BaseFragment)
    fun inject(viewModel: NYTDataViewModel)

}