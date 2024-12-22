package com.nitroxen.androidextra.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitroxen.androidextra.R

class CategoryAdapter(private val category: List<TaskCategory>, private val onItemSelected:(Int)->Unit):
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.render(category[position], onItemSelected)
    }

    override fun getItemCount(): Int = category.size
}
