package com.jesse.listasecoleoces

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnExecutar: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    btnExecutar = findViewById<Button>(R.id.button)
    textResultado = findViewById<TextView>(R.id.textView)


        // função lambda
        val funcao = { nome: String ->
            Log.d("Função lambda", "$nome" )
        }

        btnExecutar.setOnClickListener {
            textResultado.text = "jesse"

          val toast = Toast.makeText(
                this,
                "Botão clicado" ,
                Toast.LENGTH_LONG
            )
            // seria um forma de fazer um toast na parte superiro , mas não funciona em apis mais novas
            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 150)
            toast.show()


            funcao("jesse")
        }

    }

//    fun cliqueButao(view: View){
//        textResultado.text = "jesse"
//    }


}