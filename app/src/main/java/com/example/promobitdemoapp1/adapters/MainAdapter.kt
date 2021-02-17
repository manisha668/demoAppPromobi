package com.example.promobitdemoapp1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.promobitdemoapp1.R
import com.example.promobitdemoapp1.network.modal.NYTData
import kotlinx.android.synthetic.main.list_layout.view.*

class MainAdapter(private val items : ArrayList<NYTData>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       return MainViewHolder(LayoutInflater.from(parent.context)
           .inflate(R.layout.list_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
              holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class MainViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView) {
        fun bind(data : NYTData){
             data.response.docs.forEach {
               it.document_type.forEach {
                   itemView.list_title_text_1.text = it.toString()
               }
           }
            data.response.docs.forEach {
                it.headline.print_headline.forEach {
                    itemView.list_title_text_2.text = it.toString()
                }
            }
            data.response.docs.forEach {
                it.multimedia.forEach {
                   itemView.list_title_text_3.text = it.caption.toString()
                }
            }
        }
    }
}