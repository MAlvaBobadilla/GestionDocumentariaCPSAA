package com.example.gestindocumentariacpsaa.presentation.ui.myqr.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.databinding.ItemCategoriesBinding
import com.example.gestindocumentariacpsaa.presentation.models.CategoriesModel

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCategoriesBinding.bind(view)

    fun render(categories: CategoriesModel) {
        binding.tvTitle.text = "Categoria : ${categories.categories}"
        binding.tvCounter.text = "Total de animes : ${categories.count}"
        
    }
}