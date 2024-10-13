package com.example.gestindocumentariacpsaa.data

import android.util.Log
import com.example.gestindocumentariacpsaa.data.models.Response
import com.example.gestindocumentariacpsaa.data.retrofit.RetrofitHelper
import com.example.gestindocumentariacpsaa.data.retrofit.Services
import com.example.gestindocumentariacpsaa.domain.Repository
import com.example.gestindocumentariacpsaa.domain.model.PlantaModel

class RepositoryImpl : Repository {

    val BASE_URL_GET = "https://script.google.com/macros/s/AKfycbzn2dXseU62RlZgyJerRzHhUfi20Dllmfu3kn9UyDwxnP07Rx1LFz5gCRiJnHNhXG124Q/"

    private val apiService: Services = RetrofitHelper.getRetrofit(BASE_URL_GET).create(Services::class.java)

    override suspend fun getUserValidation(usuario: String): Response? {
        runCatching {
            apiService.getUser(id = usuario)
        }
            .onSuccess { return it }
            .onFailure {  Log.e("Error", "Error de Servicio Validacion: ${it.message}")  }
        return null
    }

    override suspend fun getPlantas(): Response? {
        runCatching {
            apiService.getPlantas()
        }
            .onSuccess { return it}
            .onFailure { Log.e("Error", "Error de Servicio Plantas: ${it.message}") }
        return null
    }

    override suspend fun getAreas(planta: String): Response? {
        runCatching {
            apiService.getAreas(planta = planta)
        }
            .onSuccess { return it }
            .onFailure { Log.e("Error", "Error de Servicio Areas: ${it.message}") }
        return null
    }
}