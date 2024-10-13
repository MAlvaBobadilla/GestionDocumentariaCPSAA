package com.example.gestindocumentariacpsaa.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.R
import com.example.gestindocumentariacpsaa.data.models.Response

class PlantaAdapter(
    private var items: List<Response.Plantas> = emptyList(),
    private var onItemSelected: (Response.Plantas) -> Unit
) :
    RecyclerView.Adapter<PlantasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantasViewHolder {
        return PlantasViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_general, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: PlantasViewHolder, position: Int) {
        holder.render(items[position], onItemSelected)
    }

    fun updateList(list: List<Response.Plantas>) {
        items = list
        notifyDataSetChanged()
    }
}