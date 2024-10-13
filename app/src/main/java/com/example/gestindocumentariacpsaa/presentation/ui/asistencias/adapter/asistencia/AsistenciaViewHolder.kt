package com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.asistencia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.databinding.ItemCategoriesBinding
import com.example.gestindocumentariacpsaa.databinding.ItemGeneralBinding
import com.example.gestindocumentariacpsaa.presentation.models.AsistenciaModel

class AsistenciaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCategoriesBinding.bind(view)

    fun render(Asistencia: AsistenciaModel) {
        binding.tvDocumento.text = "Documento: ${Asistencia.dni}"
        binding.tvColaborador.text = "Colaborador ${position}"
        binding.tvRegistro. text = "Registro: ${Asistencia.asistencia}"
        binding.tvHora.text = "Hora: ${Asistencia.hora}"
    }
}