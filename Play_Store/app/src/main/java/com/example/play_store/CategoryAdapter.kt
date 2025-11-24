package com.example.play_store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val categories: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.txtCategoryTitle)
        val rvChild: RecyclerView = view.findViewById(R.id.rvApps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]

        holder.title.text = category.title

        val layoutManager = if (category.type == 0) {
            LinearLayoutManager(holder.itemView.context, RecyclerView.VERTICAL, false)
        } else {
            LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL, false)
        }

        holder.rvChild.layoutManager = layoutManager
        holder.rvChild.adapter = AppAdapter(category.apps, category.type)
    }

    override fun getItemCount(): Int = categories.size
}