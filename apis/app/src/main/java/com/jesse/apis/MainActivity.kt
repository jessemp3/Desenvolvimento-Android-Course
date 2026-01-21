package com.jesse.apis

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.apis.databinding.ActivityMainBinding
import com.jesse.apis.service.EnderecoApi
import com.jesse.apis.service.RetrofitHelper.Companion.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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
            button.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    recuperaEndereco()
                }
            }
        }
    }

    private suspend fun recuperaEndereco(){
       val enderecoApi =  retrofit.create<EnderecoApi>(EnderecoApi::class.java)
        enderecoApi.recuperarEndereco()
    }
}