package com.example.music_app4e

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.music_app4e.model.Pelicula
import com.example.music_app4e.model.PeliculasResponse
import com.example.music_app4e.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var tvEstado : TextView
    private lateinit var contenedorAccion : LinearLayout
    private lateinit var contenedorComedia : LinearLayout
    private lateinit var contenedorTerror : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvEstado = findViewById(R.id.tvEstado)
        contenedorAccion = findViewById(R.id.contenedorAccion)
        contenedorComedia = findViewById(R.id.contenedorComedia)
        contenedorTerror = findViewById(R.id.contenedorTerror)

        // 3 CONSULTAS AL API (UNA POR GÉNERO)
        // 4401 = Acción y Aventura, 4404 = Comedia, 4408 = Terror
        obtenerPeliculas(4401, contenedorAccion)
        obtenerPeliculas(4404, contenedorComedia)
        obtenerPeliculas(4408, contenedorTerror)

    }

    //METODO PARA OBTENER LA RESPUESTA DEL API
    private fun obtenerPeliculas(genero : Int, contenedor : LinearLayout){

        RetrofitClient.api.obtenerPeliculasGenero(

            pais = "mx",
            limite = 15,
            genero = genero

        ).enqueue(object : Callback<PeliculasResponse>{

            //METODO EN CASO DE OBTENER UNA RESPUESTA EXITOSA
            override fun onResponse(call: Call<PeliculasResponse>, response: Response<PeliculasResponse>){

                //VARIABLE QUE OBTIENE LA LISTA DE PELÍCULAS DEL API
                val peliculas = response.body()?.feed?.entry ?: emptyList()

                if(peliculas.isNotEmpty()){

                    tvEstado.text = "Películas cargadas"
                    mostrarPeliculas(peliculas, contenedor)

                }else{
                    Toast.makeText(this@HomeActivity, "No Hay Resultados", Toast.LENGTH_SHORT).show()

                }

            }

            //METODO EN CASO DE UNA RESPUESTA FALLIDA
            override fun onFailure(call: Call<PeliculasResponse>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Error en el API", Toast.LENGTH_SHORT).show()
            }

        })

    }

    // MÉTODO PARA MOSTRAR UNA LISTA DE PELÍCULAS
    private fun mostrarPeliculas(peliculas : List<Pelicula>, contenedor : LinearLayout){

        // Eliminar los elementos duplicados
        contenedor.removeAllViews()

        // Ciclo que recorre las películas
        for(pelicula in peliculas){

            // Crear la tarjeta (item_pelicula)
            val vistaPelicula = layoutInflater.inflate(
                R.layout.item_pelicula,
                contenedor,
                false
            )

            // Obtener los elementos de la tarjeta
            val imgPoster = vistaPelicula.findViewById<ImageView>(R.id.imgPoster)
            val tvTitulo = vistaPelicula.findViewById<TextView>(R.id.tvTitulo)
            val tvDirector = vistaPelicula.findViewById<TextView>(R.id.tvDirector)

            tvTitulo.text = pelicula.nombre?.label ?: "Película sin nombre"
            tvDirector.text = pelicula.director?.label ?: "Director desconocido"

            Glide.with(this)
                .load(pelicula.obtenerPosterGrande())
                .into(imgPoster)

            contenedor.addView(vistaPelicula)

        }

    }

}
