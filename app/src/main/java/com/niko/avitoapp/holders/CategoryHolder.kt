package com.niko.avitoapp.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niko.avitoapp.R

class CategoryHolder(view: View): RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.categoryImage)
    val textView: TextView = view.findViewById(R.id.categotyText)
}