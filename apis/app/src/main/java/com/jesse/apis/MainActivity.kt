package com.jesse.apis

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.apis.databinding.ActivityMainBinding
import com.jesse.apis.model.EnderecoPlugin
import com.jesse.apis.model.Postagem
import com.jesse.apis.service.EnderecoApi
import com.jesse.apis.service.PostagemApi
import com.jesse.apis.service.RetrofitHelper.Companion.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

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

        with(binding) {
           button.setOnClickListener {
               Log.d("button", "button clicked")
               CoroutineScope(Dispatchers.IO).launch{
//                   recuperaEndereco()
                   recuperarPostagens()
           }
        }
        }
    }

    private suspend fun recuperarPostagens() {
        var retorno: Response<List<Postagem>>? = null


        try {
            val postagemApi = retrofit.create<PostagemApi>(PostagemApi::class.java)
            retorno = postagemApi.recuperarPostagens()

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("erro", e.message.toString())
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val post = retorno.body()
                post?.forEach { post ->
                    Log.i("Post", post.toString())
                }
            }
        }
    }

    private suspend fun recuperaEndereco() {
        var retorno: Response<EnderecoPlugin>? = null
        val cepDigitado = binding.editTextText.text.toString()


        try {
            val enderecoApi = retrofit.create<EnderecoApi>(EnderecoApi::class.java)
            retorno = enderecoApi.recuperarEndereco(cepDigitado)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("erro", e.message.toString())
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val endereco = retorno.body()
                Log.i("endereco", endereco.toString())
            }
        }
    }
}