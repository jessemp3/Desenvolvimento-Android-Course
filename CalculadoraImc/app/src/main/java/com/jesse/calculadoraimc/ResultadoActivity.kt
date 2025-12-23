package com.jesse.calculadoraimc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.calculadoraimc.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent != null) {
            val peso = intent.getDoubleExtra("peso", 0.0)
            val altura = intent.getDoubleExtra("altura", 0.0)

            binding.textViewPesoInformado.text = "Peso informado: ${peso.toString()} Kg"
            binding.textViewAlturaInformado.text = "Altura informada: ${altura.toString()} M"

            val resultado: Double = peso / (altura * altura)

            when {
                resultado < 18.5 -> binding.textViewDiagnostico.text = "Diagnóstico: Abaixo do peso"
                resultado in 18.5..24.9 -> binding.textViewDiagnostico.text = "Diagnóstico: Peso normal"
                resultado in 25.0..29.9 -> binding.textViewDiagnostico.text = "Diagnóstico: Sobrepeso"
                resultado in 30.0..39.9 -> binding.textViewDiagnostico.text = "Diagnóstico: Obesidade"
            }
        }

    }
}