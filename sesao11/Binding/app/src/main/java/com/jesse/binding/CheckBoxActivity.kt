package com.jesse.binding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.binding.databinding.ActivityCheckBoxBinding

class CheckBoxActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCheckBoxBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding){
            button5.setOnClickListener {
//                checkbox()
//            }

                confirmacao.setOnCheckedChangeListener { _, isChecked ->
                    val result = if (isChecked) "sim" else "Não"
                    textViewResultado.text = "Valor selecionado: $result"
                }}
        }


    }

    private fun checkbox(){
       val selecionado =  binding.confirmacao.isChecked
        binding.textViewResultado.text = "Valor selecionado: $selecionado"
    }

}