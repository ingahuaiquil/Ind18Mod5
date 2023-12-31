package com.example.ind18mod5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ind18mod5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Sharepreference
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Sharepreference nombre como se guardaran los datos
        mSharedPreferences=getSharedPreferences("cookie", Context.MODE_PRIVATE)

        //Rescatar la información
        binding.btGuardar.setOnClickListener(){
            val texto=binding.etTexto.toString()
            val entero=binding.etEntero.toString()
            val decimal=binding.etDecimal.toString()
            val boleano= binding.switch1.isChecked

            // guardar datos
            guardarDatos(texto, entero.toInt(), decimal.toFloat(), boleano)
        }

        //Mostrar datos
        binding.btMostrar.setOnClickListener{
            mostrarDatos()
        }

        binding.btBorrar.setOnClickListener{
            borrarDatos()
        }
    }
    // metodo para guardar datos
    private fun guardarDatos(texto: String, entero: Int, decimal: Float, boleano: Boolean){
        //
        mSharedPreferences.edit().putString("miTexto", texto).apply()
        mSharedPreferences.edit().putInt("miEntero", entero).apply()
        mSharedPreferences.edit().putFloat("miDecimal", decimal).apply()
        mSharedPreferences.edit().putBoolean("miBooleano", boleano).apply()


    }

    //Mostrar datos
    private fun mostrarDatos() {
        val texto=mSharedPreferences.getString("miTexto","")
        val entero=mSharedPreferences.getInt("miEntero", 0)
        val decimal=mSharedPreferences.getFloat("miDecimal", 0.0f)
        val boleano=mSharedPreferences.getBoolean("miBooleano", false)

        binding.tvTexto.text=texto
        binding.tvEntero.text=entero.toString()
        binding.tvDecimal.text=decimal.toString()
        binding.tvSwitch.text=boleano.toString()

        binding.switch1.isChecked=boleano


    }

    private fun borrarDatos() {
        binding.tvTexto.text=""
        binding.tvEntero.text=""
        binding.tvDecimal.text=""
        binding.tvSwitch.text=""

        binding.etTexto.text.clear()
        binding.etEntero.text.clear()
        binding.etDecimal.text.clear()
        binding.switch1.isChecked=false

        mSharedPreferences.edit().clear().apply()
    }





}
