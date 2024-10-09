package com.example.gestindocumentariacpsaa.domain

import com.example.gestindocumentariacpsaa.data.models.Response
import com.example.gestindocumentariacpsaa.presentation.models.CategoriesModel
import com.example.gestindocumentariacpsaa.presentation.models.HomeModel

interface Repository {
    suspend fun getUserValidation(usuario: String): Response?
    suspend fun getPlantas(): Response?
    suspend fun getAreas(planta: String): Response?
}