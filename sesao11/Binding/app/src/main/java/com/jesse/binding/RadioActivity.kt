package com.jesse.binding

import android.os.Bundle
import android.widget.Toast
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
//                radioButton()
                switchButton()
            }

            toggleButton.setOnClickListener {
                if(toggleButton.isChecked){
                    Toast.makeText(this@RadioActivity , "Toggle selecionado" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

private fun ActivityRadioBinding.switchButton() {
    val selecionado = switch1.isChecked
    val toogleSelecionado = toggleButton.isChecked


    textViewResultado.text = "Switch: ${selecionado} \nToggle: ${toogleSelecionado}"
}

private fun ActivityRadioBinding.radioButton() {
   val selecionadoMas = radioButtonMas.isChecked
    if(selecionadoMas){
        textViewResultado.text = "Masculino"
    }else{
        textViewResultado.text = "Feminino"
    }
}
