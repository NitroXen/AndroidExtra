package com.nitroxen.androidextra.todolist

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nitroxen.androidextra.R

class CategoryViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val tvCategoryName:TextView = view.findViewById(R.id.tvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)
    private val cvCategory:CardView = view.findViewById(R.id.cvCategory)

    fun render (taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        val color = if(taskCategory.isSelected) R.color.todo_background_card else R.color.todo_background_disabled

        cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context,color))

        itemView.setOnClickListener{onItemSelected(layoutPosition)}

        when(taskCategory){
            TaskCategory.Business->{
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context,R.string.business)
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.todo_business_category))
            }
            TaskCategory.Personal->{
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context,R.string.personal)
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.todo_personal_category))
            }
            TaskCategory.Other->{
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context,R.string.other)
                divider.setBackgroundColor(ContextCompat.getColor(divider.context,R.color.todo_other_category))
            }
        }
    }

}