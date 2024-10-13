package com.example.gestindocumentariacpsaa.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.R

class AreasAdapter(
    private var items: List<String> = emptyList(),
    private var onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<AreasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreasViewHolder {
        return AreasViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_general, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: AreasViewHolder, position: Int) {
        holder.render(items[position], onItemSelected)
    }

    fun updateList(list: List<String>) {
        items = list
        notifyDataSetChanged()
    }
}