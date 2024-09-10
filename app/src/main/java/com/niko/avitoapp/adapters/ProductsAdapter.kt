package com.niko.avitoapp.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.niko.avitoapp.R
import com.niko.avitoapp.holders.ProductHolder
import com.squareup.picasso.Picasso
import domain.models.Product
import javax.inject.Inject

class ProductsAdapter @Inject constructor() : ListAdapter<Product, ProductHolder>(Comparator()) {
    var getDetailInfo: ((id: String) -> Unit)? = null
    var uploadData: (() -> Unit)? = null

    class Comparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_layout, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val images = getItem(position).images
        val name = getItem(position).name
        val oldPrice = getItem(position).price
        val newPrice = getItem(position).discountedPrice
        if (images?.get(0) == null)
            holder.productImage.setImageResource(R.drawable.page_not_found_svgrepo_com)
        else
            Picasso.get().load(images[0]).into(holder.productImage)
        if(images?.size != null && images.size > 2)
            Picasso.get().load(images[1]).into(holder.productImage)
        if(name != null && name.length > 30)
            holder.productName.text = String.format(holder.view.context.getString(R.string.short_string),name)
        else
            holder.productName.text = name
        if (oldPrice == null)
            holder.oldPrice.visibility = View.GONE
        else {
            holder.oldPrice.text = String.format(holder.view.context.getString(R.string.price_string),oldPrice.toString())
            holder.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.oldPrice.visibility = View.VISIBLE
        }
        holder.newPrice.text = String.format(holder.view.context.getString(R.string.price_string),newPrice.toString())
        holder.itemView.setOnClickListener {
            getDetailInfo?.let { getInfo -> getInfo(getItem(position).images.toString()) }
        }
        if (currentList.size-10 == position)
            uploadData?.let { it() }
    }

}