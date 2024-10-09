package com.example.gestindocumentariacpsaa.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {

        private val loginInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val httpCliente = OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
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