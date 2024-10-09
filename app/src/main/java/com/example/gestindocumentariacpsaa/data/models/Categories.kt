package com.example.gestindocumentariacpsaa.data.models


import com.example.gestindocumentariacpsaa.presentation.models.CategoriesModel
import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("count")
        val count: Int,
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
    fun toCategoriesModel(): List<CategoriesModel>{
        return data.map {
            CategoriesModel(
                categories = it.name,
                count = it.count
            )
        }
    }
}