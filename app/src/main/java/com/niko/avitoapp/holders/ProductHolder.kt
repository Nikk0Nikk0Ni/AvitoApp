package com.niko.avitoapp.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niko.avitoapp.R

class ProductHolder(val view: View): RecyclerView.ViewHolder(view) {
    val productImage : ImageView = view.findViewById(R.id.productImage)
    val productName : TextView = view.findViewById(R.id.productName)
    val oldPrice : TextView = view.findViewById(R.id.oldPrice)
    val newPrice : TextView = view.findViewById(R.id.newPrice)
}