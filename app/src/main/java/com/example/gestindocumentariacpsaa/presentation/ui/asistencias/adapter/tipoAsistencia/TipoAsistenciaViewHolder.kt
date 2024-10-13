package com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.tipoAsistencia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.databinding.ItemGeneralBinding

class TipoAsistenciaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemGeneralBinding.bind(view)

    fun render(tipoAsistencia: String, onItemSelected: (String) -> Unit) {
        binding.tvItem.text = tipoAsistencia

        binding.root.setOnClickListener{
            onItemSelected(tipoAsistencia)
        }
    }
}