package com.jesse.cep

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.cep.databinding.ActivityMainBinding
import com.jesse.cep.model.Cep
import com.jesse.cep.service.EnderecoAPI
import com.jesse.cep.service.RetrofitHelper.Companion.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
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
            btnPesquisar.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    getEndereco()
                }
            }
        }
    }

    private suspend fun getEndereco(){
        var retorno: Response<Cep>? = null
        val cep = binding.tIL.editText?.text.toString()


        try {
            val response = retrofit.create(EnderecoAPI::class.java)
            retorno = response.getEndereco(cep)
        }catch (e: Exception){
            e.printStackTrace()
            Log.d("erro", "erro: ${e.message}")
        }

        if (retorno != null){
            if (retorno.isSuccessful) {
                val endereco = retorno.body()
                Log.i("endereco", endereco.toString())
                withContext(Dispatchers.Main){
                    binding.textViewResposta.text = "Rua ${endereco?.logradouro} Bairro ${endereco?.bairro}"
                }
            }
        }
    }
}