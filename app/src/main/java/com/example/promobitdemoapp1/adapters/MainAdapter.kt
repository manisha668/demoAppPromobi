package com.example.promobitdemoapp1.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.promobitdemoapp1.R
import com.example.promobitdemoapp1.adapters.MainAdapter.MainViewHolder
import com.example.promobitdemoapp1.network.modal.Doc

class MainAdapter(private val items : ArrayList<Doc>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       return MainViewHolder(LayoutInflater.from(parent.context)
           .inflate(R.layout.list_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }
    class MainViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView) {

    }

}