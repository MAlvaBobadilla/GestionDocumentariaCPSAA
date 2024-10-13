package com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.asistencia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.R
import com.example.gestindocumentariacpsaa.presentation.models.AsistenciaModel

class AsistenciaAdapter(
    private var items: List<AsistenciaModel> = emptyList()
) :
    RecyclerView.Adapter<AsistenciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsistenciaViewHolder {
        return AsistenciaViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: AsistenciaViewHolder, position: Int) {
        holder.render(items[position])
    }

    fun updateList(list: List<AsistenciaModel>) {
        items = list
        notifyDataSetChanged()
    }
}