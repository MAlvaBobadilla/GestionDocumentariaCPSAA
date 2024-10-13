package com.example.gestindocumentariacpsaa.domain

import com.example.gestindocumentariacpsaa.data.models.Response
import com.example.gestindocumentariacpsaa.domain.model.PlantaModel

interface Repository {
    suspend fun getUserValidation(usuario: String): Response?
    suspend fun getPlantas(): Response?
    suspend fun getAreas(planta: String): Response?
}