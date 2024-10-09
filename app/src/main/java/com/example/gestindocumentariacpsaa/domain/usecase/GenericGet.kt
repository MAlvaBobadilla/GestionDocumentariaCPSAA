package com.example.gestindocumentariacpsaa.domain.usecase

import com.example.gestindocumentariacpsaa.data.RepositoryImpl
import com.example.gestindocumentariacpsaa.domain.Repository

class GenericGet() {

    private val repository: Repository = RepositoryImpl()

    suspend fun getUserValidation(id: String) = repository.getUserValidation(usuario = id)

    suspend fun getPlantas() = repository.getPlantas()

    suspend fun getAreas(planta: String) = repository.getAreas(planta = planta)

}