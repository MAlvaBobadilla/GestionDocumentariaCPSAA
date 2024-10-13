package com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.tipoAsistencia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.R

class TipoAsistenciaAdapter(
    private var items: List<String> = emptyList(),
    private var onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<TipoAsistenciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoAsistenciaViewHolder {
        return TipoAsistenciaViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_general, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: TipoAsistenciaViewHolder, position: Int) {
        holder.render(items[position], onItemSelected)
    }

    fun updateList(list: List<String>) {
        items = list
        notifyDataSetChanged()
    }
}