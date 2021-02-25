package com.example.promobitdemoapp1.customui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.promobitdemoapp1.R
import java.util.jar.Attributes

class LoadingIndicator (context: Context, attributes: AttributeSet):CardView(context,attributes){

    init {
        LayoutInflater.from(context).inflate( R.layout.layout_loading_indicator,this,false)
    }
}