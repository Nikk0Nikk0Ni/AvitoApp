package com.niko.avitoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.niko.avitoapp.R
import com.niko.avitoapp.holders.CategoryHolder
import com.niko.avitoapp.models.CategoryItem
import javax.inject.Inject

class CategoryAdapter @Inject constructor() : ListAdapter<CategoryItem, CategoryHolder>(CategoryComparator()) {
    var onCategoryClick: ((String?)->Unit)? = null
    class CategoryComparator : DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_layout, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.imageView.setImageResource(getItem(position).image)
        holder.textView.text = getItem(position).text
        holder.itemView.setOnClickListener{
            onCategoryClick?.let {
                it(getItem(position).category)
            }
        }
    }
}