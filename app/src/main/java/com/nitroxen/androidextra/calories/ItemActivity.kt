package com.nitroxen.androidextra.calories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nitroxen.androidextra.R

class ActivityViewHolder(view:View):RecyclerView.ViewHolder(view){

    val cvAct:CardView = view.findViewById(R.id.cvAct)
    val tvAct1:TextView = view.findViewById(R.id.tvAct1)
    val tvAct2:TextView = view.findViewById(R.id.tvAct2)


    fun render(activity: Activity, onClicked: (Int) -> Unit){

        val color = if (activity.isSelected) R.color.background_component else R.color.background_cardview

        cvAct.setBackgroundColor(ContextCompat.getColor(cvAct.context,color))

        tvAct1.text = activity.text1
        tvAct2.text = activity.text2

        itemView.setOnClickListener{onClicked(layoutPosition)}

    }
}

class ActivityAdapter(private val activities: List<Activity>,private val onClicked: (Int) -> Unit): RecyclerView.Adapter<ActivityViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.render(activities[position], onClicked)
    }

    override fun getItemCount(): Int =activities.size

}