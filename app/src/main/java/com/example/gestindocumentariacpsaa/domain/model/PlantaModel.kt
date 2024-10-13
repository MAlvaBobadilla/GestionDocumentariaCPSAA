package com.example.gestindocumentariacpsaa.domain.model

data class PlantaModel(
    var ok: Int = 0,
    var message: String = "",
    val codplanta: List<String> = emptyList(),
    val planta: List<String> = emptyList(),
)
