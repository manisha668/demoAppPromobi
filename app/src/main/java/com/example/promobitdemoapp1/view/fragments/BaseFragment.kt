package com.example.promobitdemoapp1.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promobitdemoapp1.R
import com.example.promobitdemoapp1.adapters.MainAdapter
import com.example.promobitdemoapp1.di.component.DaggerApplicationComponent
import com.example.promobitdemoapp1.di.module.APIModule
import com.example.promobitdemoapp1.network.modal.Doc
import com.example.promobitdemoapp1.utils.constants.AppConstants
import com.example.promobitdemoapp1.viewmodel.NYTDataViewModel
import com.example.promobitdemoapp1.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_base.*
import javax.inject.Inject

class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: NYTDataViewModel
   private lateinit  var doctData : List<Doc>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.builder()
            .aPIModule(APIModule("https://api.nytimes.com/svc/archive/v1/"))
            .build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ViewModelProvider(this, viewModelFactory).get(NYTDataViewModel::class.java).also { viewModel = it }
        val month = arguments?.getString(AppConstants.MONTH_DATA)
        val year = arguments?.getString(AppConstants.YEAR_DATA)
        Log.d("BaseFragment","$month, $year")
        getApiData(month = month!!.toInt(), year = year!!.toInt())
        initView(container)
        return inflater.inflate(R.layout.fragment_base, container, false)
    }


    fun getApiData(month : Int, year: Int){
        viewModel.getNYTData(month = month, year = year)
        Log.d("BaseFragment", "month sent : $month, year sent : $year")
        viewModel.nytLiveData.observe(viewLifecycleOwner, Observer {
            doctData = it
        })
    }

    fun initView(view:ViewGroup?){
        viewModel.nytLiveData.observe(viewLifecycleOwner, Observer {
//            main_recycler_view.adapter = MainAdapter(it)
            doctData = it
            Log.d("BaseFragment", "data from api $it")
            val recyclerView = view?.findViewById<RecyclerView>(R.id.main_recycler_view)
            recyclerView?.layoutManager = LinearLayoutManager(activity)
            val adapter = MainAdapter(doctData)
            this.main_recycler_view.adapter = adapter
        })
    }
}