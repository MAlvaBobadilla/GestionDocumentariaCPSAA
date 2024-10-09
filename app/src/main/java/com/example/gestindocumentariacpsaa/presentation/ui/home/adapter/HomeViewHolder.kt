package com.example.gestindocumentariacpsaa.presentation.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.databinding.ItemHomeAnimesBinding
import com.example.gestindocumentariacpsaa.presentation.models.HomeModel
import com.squareup.picasso.Picasso

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHomeAnimesBinding.bind(view)

    fun render(animes: HomeModel) {
        binding.tvTitle.text = animes.title
        binding.tvType.text = animes.type
        binding.tvValoration.text = animes.score.toString()
        Picasso.get()
            .load(animes.img)
            .into(binding.imvAnime)
    }
}