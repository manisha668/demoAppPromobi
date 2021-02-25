package com.example.promobitdemoapp1.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.promobitdemoapp1.App
import com.example.promobitdemoapp1.R
import com.example.promobitdemoapp1.customui.LoadingIndicator
import com.example.promobitdemoapp1.network.modal.Doc
import com.example.promobitdemoapp1.utils.constants.AppConstants
import com.example.promobitdemoapp1.viewmodel.NYTDataViewModel
import com.example.promobitdemoapp1.viewmodel.ViewModelFactory
import javax.inject.Inject

class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: NYTDataViewModel
    var doctData : List<Doc>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ( activity?.application as App).getAPIComponent().inject(this)
        ViewModelProvider(this, viewModelFactory).get(NYTDataViewModel::class.java).also { viewModel = it }
        val month = arguments?.getString(AppConstants.MONTH_DATA)
        val year = arguments?.getString(AppConstants.YEAR_DATA)
        Log.d("BaseFragment","$month, $year")
        getApiData(month = month!!.toInt(), year = year!!.toInt())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView(container)
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    fun getApiData(month : Int, year: Int){
        viewModel.getNYTData(month = month, year = year)
        Log.d("BaseFragment", "month sent : $month, year sent : $year")
    }

    fun initView(view:ViewGroup?){
//        val recyclerView = view?.findViewById<RecyclerView>(R.id.main_recycler_view)
//        recyclerView?.layoutManager = LinearLayoutManager(activity)
//        val adapter = NYTDataViewModel.doctData?.let { MainAdapter(it) }
//        recyclerView?.adapter = adapter
        val loader = view?.findViewById<LoadingIndicator>(R.id.fragment_loader)
        loader?.visibility = View.VISIBLE
        viewModel.nytLiveData.observe(viewLifecycleOwner, Observer {
            list ->
            doctData = list
            //recyclerView?.adapter?.notifyDataSetChanged()
            Log.d("BaseFragment", "data from api $list")
            loader?.visibility = View.VISIBLE
         })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "error occured: $it",Toast.LENGTH_SHORT).show()
        })
    }
}