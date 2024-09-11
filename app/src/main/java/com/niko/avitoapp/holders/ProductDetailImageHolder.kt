package com.niko.avitoapp.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.niko.avitoapp.R

class ProductDetailImageHolder(view: View): RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.product_image)
}