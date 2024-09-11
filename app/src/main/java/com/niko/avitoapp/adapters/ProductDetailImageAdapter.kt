package com.niko.avitoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.niko.avitoapp.R
import com.niko.avitoapp.holders.ProductDetailImageHolder
import com.niko.avitoapp.models.ImageDetailItem
import com.squareup.picasso.Picasso

class ProductDetailImageAdapter: ListAdapter<ImageDetailItem, ProductDetailImageHolder>(Comparator()){
    class Comparator(): DiffUtil.ItemCallback<ImageDetailItem>(){
        override fun areItemsTheSame(oldItem: ImageDetailItem, newItem: ImageDetailItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ImageDetailItem,
            newItem: ImageDetailItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)
        return ProductDetailImageHolder(view)
    }

    override fun onBindViewHolder(holder: ProductDetailImageHolder, position: Int) {
        Picasso.get().load(getItem(position).image).into(holder.imageView)
    }

}