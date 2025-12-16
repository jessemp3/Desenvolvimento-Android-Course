package com.jesse.navegacao

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.navegacao.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {
    private val bingin by lazy{
        ActivityNewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bingin.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        inicializarToolBar()
    }

    private fun inicializarToolBar() {
       with(bingin){
           newToolbar.ctlLogo.visibility = View.GONE

           newToolbar.materialToolbar.title = "Upload de Video"
           newToolbar.materialToolbar.setTitleTextColor(
               getColor(R.color.white)
           )

           setSupportActionBar(newToolbar.materialToolbar)

          supportActionBar?.setDisplayHomeAsUpEnabled(true)
       }
    }
}