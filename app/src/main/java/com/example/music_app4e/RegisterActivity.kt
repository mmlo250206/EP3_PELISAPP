package com.example.music_app4e

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmarPassword: EditText
    private lateinit var btnRegistrar: Button

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrer)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword)
        etConfirmarPassword = findViewById(R.id.etConfirmarPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        dbHelper = DatabaseHelper(this)

        //Crear el evento para registrar a un usuario
        btnRegistrar.setOnClickListener{
            registrarUsuario()
        }

        //Regresar al inicio de sesion
        findViewById<TextView>(R.id.tvIniciarSesion).setOnClickListener {
            finish()
        }

    }

    //METODO PARA REGISTRAR A UN USUARIO
    private fun registrarUsuario(){
        //Obtener la informacion que escribe el usuario
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val pass = etPassword.text.toString().trim()

        val confirmar = etConfirmarPassword.text.toString().trim()

        //VALIDAR QUE EL USUSARIO LLENE LOS DATOS
        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || confirmar.isEmpty()){
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        //VALIDAR EL FORMATO DEL CORREO
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(this, "Escribe un correo válido", Toast.LENGTH_SHORT).show()
            return
        }
        //VALIDAR LA CONTRASEÑA
        if (pass.length < 6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != confirmar){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }
        //VALIDAR QUE EL CORREO NO ESTE REGISTRADO
        if (dbHelper.existeCorreo(correo)){
            Toast.makeText(this, "Ese correo ya está registrado", Toast.LENGTH_SHORT).show()
            return
        }
        val resultado = dbHelper.registrarUsuario(nombre, correo, pass)

        if (resultado){
            //SI EL RESULTADO ES VERDADERO
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()

        }
    }
}
