package com.example.promobitdemoapp1.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.promobitdemoapp1.R
import com.example.promobitdemoapp1.network.api.NYTServiceCallInterface
import com.example.promobitdemoapp1.view.fragments.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var fragment = BaseFragment()
    private val serviceCall by lazy {
        NYTServiceCallInterface.create()
    }
    private var disposable : Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var fragmentTansaction = supportFragmentManager.beginTransaction()
//        fragmentTansaction.add(R.id.main_fragment_container, fragment)
//        fragmentTansaction.commit()
       callApi()


    }

    fun callApi(){
       disposable = serviceCall.getArchieveData(2019,1)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe (
               {result ->  result.copyright.forEach {
                   textview_example.text = it.toString()
               }
               Log.d(this.localClassName, "Result fo API = $result")},
               { error("Error occured while calling api")}
               )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}