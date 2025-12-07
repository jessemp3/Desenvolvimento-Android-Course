package com.jesse.binding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //forma 1 de chamar
//    lateinit var binding: ActivityMainBinding

    //forma 2
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // forma 1
//        binding.button.setOnClickListener {
//            Toast.makeText(this , "Clicou no botão" , Toast.LENGTH_SHORT).show()
//        }

        //forma 2
        with(binding){ // funcaõ de escopo
            button.setOnClickListener {
                Toast.makeText(this@MainActivity , "Clicou no botão do with" , Toast.LENGTH_SHORT).show()
            }
            button2?.setOnClickListener { // button2 existe em um layout e em outro não , por isso ele pede a chamada segura
                Toast.makeText(this@MainActivity , "Clicou no botão 2 do with" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}