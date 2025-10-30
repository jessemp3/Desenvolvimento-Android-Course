package com.jesse.activityfragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.activityfragment.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DetalhesActivity", "onCreate chamado")
        enableEdgeToEdge()
        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val name = intent.getStringExtra("name")
//        val age = intent.getIntExtra("age", 0)
//        binding.textViewName.text = "$name and $age"


      val filme =  if(Build.VERSION.SDK_INT >= 33 ){
           intent.getSerializableExtra("filme", Filme::class.java)!!
        }else{
            intent.getSerializableExtra("filme") as Filme
        }

        binding.textViewName.text = "${filme?.nome} - ${filme?.description} - ${filme?.avaliacoes} - ${filme?.diretor} - ${filme?.distribuidora} - ${filme?.anoLancamento}"

        binding.buttonVoltar.setOnClickListener {
            finish()
        }
    }


//    override fun onStart() {
//        super.onStart()
//        Log.d("DetalhesActivity", "onStart chamado")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("DetalhesActivity", "onResume chamadoa")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("DetalhesActivity", "onPause chamado")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("DetalhesActivity", "onStop chamado")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d("DetalhesActivity", "onRestart chamado")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("DetalhesActivity", "onDestroy chamado")
//    }
}