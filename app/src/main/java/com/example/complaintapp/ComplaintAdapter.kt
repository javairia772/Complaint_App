package com.example.complaintapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView

class ComplaintAdapter(
    private val list: MutableList<Complaint>,
    private val onClick: (Complaint) -> Unit
) : RecyclerView.Adapter<ComplaintAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvCardTitle)
        val tvName: TextView = view.findViewById(R.id.tvCardName)
        val tvRoll: TextView = view.findViewById(R.id.tvCardRoll)
        val tvCategory: TextView = view.findViewById(R.id.tvCardCategory)
        val tvPriority: TextView = view.findViewById(R.id.tvCardPriority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_complaint, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val c = list[position]

        holder.tvTitle.text = c.title
        holder.tvName.text = c.studentName
        holder.tvRoll.text = c.rollNumber
        holder.tvCategory.text = c.category
        holder.tvPriority.text = c.priority

        val color = when (c.priority) {
            "Urgent" -> "#B71C1C".toColorInt()
            "High"   -> "#F44336".toColorInt()
            "Medium" -> "#FF9800".toColorInt()
            else     -> "#4CAF50".toColorInt()
        }
        holder.tvPriority.background.setTint(color)

        holder.itemView.setOnClickListener { onClick(c) }
    }

    override fun getItemCount() = list.size
}