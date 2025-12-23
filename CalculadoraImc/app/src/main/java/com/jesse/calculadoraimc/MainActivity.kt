package com.jesse.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCalcular.setOnClickListener {
           val intent = Intent(this , ResultadoActivity::class.java)


            val peso = binding.textInputEditTextPeso.text.toString()
            val altura = binding.textInputEditTextAltura.text.toString()

            if(peso.isNotEmpty() && altura.isNotEmpty()){
                intent.putExtra("peso", peso.toDouble())
                intent.putExtra("altura",  altura.toDouble())

                startActivity(intent)
            }else{
                Toast.makeText(this , "Por favor, insira peso e altura válidos.", Toast.LENGTH_SHORT).show()
            }


        }
    }
}