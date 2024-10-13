package com.example.gestindocumentariacpsaa.presentation.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.data.models.Response
import com.example.gestindocumentariacpsaa.databinding.ItemGeneralBinding

class PlantasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemGeneralBinding.bind(view)

    fun render(plantas: Response.Plantas, onItemSelected: (Response.Plantas) -> Unit) {
        binding.tvItem.text = plantas.planta

        binding.root.setOnClickListener{
            onItemSelected(plantas)
        }
    }
}