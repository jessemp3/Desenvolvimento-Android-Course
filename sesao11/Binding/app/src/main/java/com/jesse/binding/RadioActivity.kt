package com.jesse.binding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.binding.databinding.ActivityRadioBinding

class RadioActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRadioBinding.inflate(layoutInflater)
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
                radioButton()
            }
        }
    }
}

private fun ActivityRadioBinding.radioButton() {
   val selecionadoMas = radioButtonMas.isChecked
    if(selecionadoMas){
        textViewResultado.text = "Masculino"
    }else{
        textViewResultado.text = "Feminino"
    }
}
