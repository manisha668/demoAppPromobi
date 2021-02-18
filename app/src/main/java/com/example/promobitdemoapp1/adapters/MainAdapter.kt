package com.example.promobitdemoapp1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.promobitdemoapp1.R
import com.example.promobitdemoapp1.network.modal.Doc
import com.example.promobitdemoapp1.network.modal.NYTData
import kotlinx.android.synthetic.main.list_layout.view.*

class MainAdapter(private val items : List<Doc>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

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
        fun bind(data : Doc){
          data.document_type.forEach {
              itemView.list_title_text_1.text = it.toString()
          }
            itemView.list_title_text_2.text = data.headline.name.toString()
          data.lead_paragraph.forEach {
              itemView.list_title_text_3.text = it.toString()
          }
        }
    }
}