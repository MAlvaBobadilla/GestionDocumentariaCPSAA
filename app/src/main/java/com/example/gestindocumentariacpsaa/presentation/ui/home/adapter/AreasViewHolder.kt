package com.example.gestindocumentariacpsaa.presentation.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.databinding.ItemGeneralBinding

class AreasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemGeneralBinding.bind(view)

    fun render(areas: String, onItemSelected: (String) -> Unit) {
        binding.tvItem.text = areas

        binding.root.setOnClickListener{
            onItemSelected(areas)
        }
    }
}