package com.jesse.whatsapp.activitys

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.jesse.whatsapp.R
import com.jesse.whatsapp.databinding.ActivityMensagensBinding
import com.jesse.whatsapp.model.Usuario
import com.jesse.whatsapp.util.Constantes

class MensagensActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMensagensBinding.inflate(layoutInflater)
    }

    private var dadosDestinatario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recuperarDadosUsuarioDestinatario()
        iniciarlizarToolbar()
    }

    private fun iniciarlizarToolbar() {
        val toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = ""
            if(dadosDestinatario != null){
                binding.textViewTitleNameMensagem.text = dadosDestinatario!!.nome
                Glide
                    .with(this@MensagensActivity)
                    .load(dadosDestinatario!!.foto?.toUri())
                    .into(binding.imageViewPerfilMensagem)
            }
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun recuperarDadosUsuarioDestinatario() {
        val extras = intent.extras
        if (extras != null) {
            val origem = extras.getString("origem")
            if (origem == Constantes.ORIGEM_CONTATO) {
                dadosDestinatario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    extras.getParcelable("dadosDestinatario" , Usuario::class.java)
                }else{
                    extras.getParcelable(
                        "dadosDestinatario"
                    )
                }
                Log.d("TAG", "recuperarDadosUsuarioDestinatario: $dadosDestinatario")
            }else if(origem == Constantes.ORIGEM_CONVERSA){
//
                }
        }
    }
}