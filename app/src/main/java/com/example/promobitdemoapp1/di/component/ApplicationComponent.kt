package com.example.promobitdemoapp1.di.component

import com.example.promobitdemoapp1.App
import com.example.promobitdemoapp1.di.module.APIModule
import com.example.promobitdemoapp1.view.activities.MainActivity
import com.example.promobitdemoapp1.view.fragments.BaseFragment
import dagger.Component

@Component(modules = arrayOf(APIModule::class))
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: BaseFragment)
}