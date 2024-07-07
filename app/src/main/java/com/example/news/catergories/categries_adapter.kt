package com.example.news.catergories

import com.example.domain.model.Categories
import com.example.news.R

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class categries_adapter(
    var categoriesList: List<Categories>,
    var onCategorySelectedListener: OnCategorySelectedListener
) : Adapter<categries_adapter.CategoryViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_layout, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categoriesList[position]
        if (item != null) {
            holder.category_text.text = holder.itemView.context.getString(item.titleResID)
            holder.category_image.setImageResource(item.drawableResId)
            val backgroundDrawable = holder.categoryConstraintLayout.background
            if (backgroundDrawable != null) {
                backgroundDrawable.setColorFilter(
                    holder.itemView.context.getColor(item.backgroundColor),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
            holder.itemView.setOnClickListener {
                onCategorySelectedListener.onCategorySelected(item)
            }
        }

    }


    inner class CategoryViewHolder(view: View) : ViewHolder(view) {
        val category_image: ImageView = view.findViewById(R.id.iv_category_item)
        val category_text: TextView = view.findViewById(R.id.tv_category_item)
        val categoryConstraintLayout: ConstraintLayout =
            itemView.findViewById(R.id.constraint_layout)

    }
}
