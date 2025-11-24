package com.example.play_store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(
    private val apps: List<AppItem>,
    private val type: Int
) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    inner class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgApp: ImageView = view.findViewById(R.id.imgApp)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtCategory: TextView = view.findViewById(R.id.txtCategory)
        val txtMeta: TextView = view.findViewById(R.id.txtMeta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layout = if (type == 0)
            R.layout.item_layout_vertical
        else
            R.layout.item_app

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val item = apps[position]
        holder.imgApp.setImageResource(item.imageRes)
        holder.txtName.text = item.name
        holder.txtCategory.text = item.category
        holder.txtMeta.text = "${item.rating} ★ · ${item.size}"
    }

    override fun getItemCount(): Int = apps.size
}