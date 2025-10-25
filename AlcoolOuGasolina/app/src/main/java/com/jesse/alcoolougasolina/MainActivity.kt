package com.jesse.alcoolougasolina

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.alcoolougasolina.databinding.ActivityMainBinding
import kotlin.toString

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarComponentes()
        binding.buttonCalculate.setOnClickListener {
            Toast.makeText(this , "Calculando...", Toast.LENGTH_SHORT).show()
            calculandoPreco()
        }

    }

    private fun inicializarComponentes() {


    }
    private fun calculandoPreco() {
        val precoAlcool = binding.editTextAlcohol.text.toString()
        val precoGasolina = binding.editTextGasoline.text.toString()


        val resultValidaction = validarCampos(precoAlcool, precoGasolina)
        if(resultValidaction){
            val valorAlcool = precoAlcool.toDouble()
            val valorGasolina = precoGasolina.toDouble()

            val resultadoPreco = valorAlcool / valorGasolina

            if (resultadoPreco >= 0.7) {
                binding.textViewResult.text = "Melhor utilizar Gasolina"
            } else {
                binding.textViewResult.text = "Melhor utilizar Álcool"
            }
        }

    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {

        binding.editTextAlcohol.error = null
        binding.editTextGasoline.error = null


        if(pAlcool.isEmpty()){
            Toast.makeText(this , "Preencha o preço do álcool" , Toast.LENGTH_SHORT).show()
            binding.editTextAlcohol.error = "Campo obrigatório"
            return false
        }else if(pGasolina.isEmpty()){
            Toast.makeText(this , "Preencha o preço da gasolina" , Toast.LENGTH_SHORT).show()
            binding.editTextGasoline.error = "Campo obrigatório"
            return false
        }


        return true
    }


}

