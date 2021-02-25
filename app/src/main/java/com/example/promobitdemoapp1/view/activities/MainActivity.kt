package com.example.promobitdemoapp1.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.promobitdemoapp1.App
import com.example.promobitdemoapp1.R
import com.example.promobitdemoapp1.network.api.NYTServiceCallInterface
import com.example.promobitdemoapp1.utils.constants.AppConstants
import com.example.promobitdemoapp1.view.fragments.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity() : AppCompatActivity() {
    @Inject
    lateinit var retrofit: Retrofit
    var fragment = BaseFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val bundle = Bundle()
        val yearString = textview_year.text
        val monthString = textview_month.text

        button_view.setOnClickListener(View.OnClickListener {
            bundle.putString(AppConstants.YEAR_DATA, yearString.toString())
            bundle.putString(AppConstants.MONTH_DATA, monthString.toString())
            Log.d(this.localClassName, " year is : $yearString, month is : $monthString")
            fragment.arguments = bundle
            val fragmentTansaction = supportFragmentManager.beginTransaction()
            fragmentTansaction.replace(R.id.main_fragment_container, fragment)
            fragmentTansaction.commit()
        })
    }
}