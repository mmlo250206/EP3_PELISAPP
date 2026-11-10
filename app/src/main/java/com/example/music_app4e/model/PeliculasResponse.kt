package com.example.music_app4e.model

// ARCHIVO PARA DETECTAR LAS RESPUESTAS DEL API

data class PeliculasResponse(
    val feed: Feed?
)

// "entry" ES LA LISTA DE PELÍCULAS
data class Feed(
    val entry: List<Pelicula>?
)
