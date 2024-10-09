package com.example.gestindocumentariacpsaa.data.retrofit

import com.example.gestindocumentariacpsaa.data.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    //Validación de Usuario
    @GET("exec")
    suspend fun getUser(
        @Query("api") api: String = "getUser",
        @Query("id") id: String
    ): Response

    //Get Plantas
    @GET("exec")
    suspend fun getPlantas(@Query("api") api: String = "getPlantas"): Response

    //Get Areas
    @GET("exec")
    suspend fun getAreas(
        @Query("api") api: String = "getAreas",
        @Query("codPlanta") planta: String
    ): Response

}