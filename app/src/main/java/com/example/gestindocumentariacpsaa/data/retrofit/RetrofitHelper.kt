package com.example.gestindocumentariacpsaa.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper {
    companion object {

        private val loginInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val httpCliente = OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // Aumenta el tiempo de conexión
            .readTimeout(30, TimeUnit.SECONDS) // Aumenta el tiempo de lectura
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        fun getRetrofit(url: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpCliente)
                .build()
        }
    }
}