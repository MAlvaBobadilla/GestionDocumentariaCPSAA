package com.example.gestindocumentariacpsaa.presentation.ui.myqr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.R
import com.example.gestindocumentariacpsaa.presentation.models.CategoriesModel

class CategoriesAdapter(var items: List<CategoriesModel> = emptyList()) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(items[position])
    }

    fun updateList(list: List<CategoriesModel>) {
        items = list
        notifyDataSetChanged()
    }

}