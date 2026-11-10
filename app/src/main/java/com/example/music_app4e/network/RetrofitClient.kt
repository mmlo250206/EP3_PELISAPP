package com.example.music_app4e.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// ARCHIVO QUE CONSTRUYE LA URL Y CONECTA LA API

object RetrofitClient {

    private const val BASE_URL ="https://itunes.apple.com/"

    // VARIABLE QUE CONSTRUYE LA PETICION
    val api: PeliculasApi by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PeliculasApi::class.java)

    }

}