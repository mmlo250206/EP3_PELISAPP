package com.example.music_app4e.model

import com.google.gson.annotations.SerializedName

// ARCHIVO CON LOS DATOS DE CADA PELÍCULA QUE REGRESA EL API

data class Pelicula(
    @SerializedName("im:name") val nombre: Etiqueta?,
    @SerializedName("im:image") val imagenes: List<Etiqueta>?,
    @SerializedName("im:artist") val director: Etiqueta?,
    @SerializedName("summary") val sinopsis: Etiqueta?,
    @SerializedName("im:releaseDate") val fechaEstreno: Etiqueta?,
    @SerializedName("category") val categoria: Categoria?
){

    // MÉTODO PARA OBTENER EL PÓSTER CON MAYOR RESOLUCIÓN
    fun obtenerPosterGrande() : String {
        val url = imagenes?.lastOrNull()?.label ?: return ""
        return url.substringBeforeLast("/") + "/400x600bb.jpg"
    }

}

// EL API GUARDA CADA TEXTO DENTRO DE UN "label"
data class Etiqueta(
    val label: String?
)

// GÉNERO DE LA PELÍCULA (Acción y Aventura, Comedia, Terror...)
data class Categoria(
    val attributes: AtributosCategoria?
)

data class AtributosCategoria(
    val label: String?
)
