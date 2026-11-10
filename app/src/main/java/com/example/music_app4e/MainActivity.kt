package com.example.music_app4e

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        val etCorreo = findViewById<EditText>(R.id.etCorreoLogin)
        val etPassword = findViewById<EditText>(R.id.etPasswordLogin)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        //INICIAR SESION CON LOS USUARIOS GUARDADOS EN LA BD
        btnIngresar.setOnClickListener {
            val usuario = etCorreo.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            if (usuario.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (dbHelper.validarUsuario(usuario, pass)) {
                Toast.makeText(this, "Bienvenido $usuario", Toast.LENGTH_SHORT).show()
                //IR A LA PANTALLA PRINCIPAL
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)
        //crear evento para ir a otra pantalla
        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
