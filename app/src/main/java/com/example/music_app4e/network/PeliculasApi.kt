package com.example.music_app4e.network

import com.example.music_app4e.model.PeliculasResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// ARCHIVO PARA CONEXIÓN A INTERNET

interface PeliculasApi {

    // MÉTODO PARA CREAR EL ENLACE DEL API
    // Ejemplo: https://itunes.apple.com/mx/rss/topmovies/limit=15/genre=4401/json
    @GET("{pais}/rss/topmovies/limit={limite}/genre={genero}/json")
    fun obtenerPeliculasGenero(
        @Path("pais") pais : String,
        @Path("limite") limite : Int,
        @Path("genero") genero : Int
    ) : Call<PeliculasResponse>

}
