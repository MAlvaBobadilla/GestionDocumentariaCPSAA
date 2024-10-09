package com.example.gestindocumentariacpsaa.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.R
import com.example.gestindocumentariacpsaa.data.models.Anime
import com.example.gestindocumentariacpsaa.presentation.models.HomeModel

class HomeAdapter(
    var items: List<HomeModel> = emptyList()
) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_animes, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.render(items[position])
    }

    fun updateList(list: List<HomeModel>) {
        items = list
        notifyDataSetChanged()
    }
}