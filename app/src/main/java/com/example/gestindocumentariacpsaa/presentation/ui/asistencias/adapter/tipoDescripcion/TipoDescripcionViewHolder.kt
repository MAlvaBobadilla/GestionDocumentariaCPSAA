package com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.tipoDescripcion

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.databinding.ItemGeneralBinding

class TipoDescripcionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemGeneralBinding.bind(view)

    fun render(tipoDescripcion: String, onItemSelected: (String) -> Unit) {
        binding.tvItem.text = tipoDescripcion

        binding.root.setOnClickListener{
            onItemSelected(tipoDescripcion)
        }
    }
}