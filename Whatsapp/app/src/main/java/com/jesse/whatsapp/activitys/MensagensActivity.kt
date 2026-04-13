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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.jesse.whatsapp.R
import com.jesse.whatsapp.databinding.ActivityMensagensBinding
import com.jesse.whatsapp.model.Mensagem
import com.jesse.whatsapp.model.Usuario
import com.jesse.whatsapp.util.Constantes
import com.jesse.whatsapp.util.exibirMensagens

class MensagensActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMensagensBinding.inflate(layoutInflater)
    }


    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private var dadosDestinatario: Usuario? = null
    private lateinit var snapshotListener: ListenerRegistration

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
        initClicks()
        inicializarListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        snapshotListener.remove()
    }

    private fun inicializarListeners() {
        val idUsuarioRemetente = firebaseAuth.currentUser?.uid
        val idUsuarioDestinatario = dadosDestinatario?.id

        if(idUsuarioRemetente != null && idUsuarioDestinatario != null) {
          snapshotListener = firestore.collection(Constantes.MENSAGENS)
                .document(idUsuarioRemetente)
                .collection(idUsuarioDestinatario)
                .orderBy("data", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshots, exception ->
                    if(exception != null){
                        exibirMensagens("Erro ao carregar mensagens")
                        return@addSnapshotListener
                    }

                    val listaMensagens = mutableListOf<Mensagem>()
                    val documentos = snapshots?.documents

                    documentos?.forEach { documento ->
                        val mensagem = documento.toObject(Mensagem::class.java)

                        if(mensagem != null){
                            listaMensagens.add(mensagem)
                            Log.d("TAG", "inicializarListeners: ${mensagem.mensagem}")
                        }
                    }

                    // lista
                    if(listaMensagens.isNotEmpty()){

                    }
                }
        }
    }

    private fun initClicks() {
        binding.fabEnviar.setOnClickListener {
            val mensagem = binding.editTextMensagem.text.toString()
            salvarMensagem(mensagem)
        }
    }

    fun salvarMensagem(mensagem: String) {
      if(mensagem.isNotEmpty()){
          val idUsuarioRemetente = firebaseAuth.currentUser?.uid
          val idUsuarioDestinatario = dadosDestinatario?.id

          if(idUsuarioRemetente != null && idUsuarioDestinatario != null) {
              val mensagem = Mensagem(
                  idUsuarioRemetente,
                  mensagem
              )
              //salvando pro remetente
              salvarMensagemFireStore(idUsuarioRemetente, idUsuarioDestinatario, mensagem)
              //salvando pro destinatario
              salvarMensagemFireStore(idUsuarioDestinatario, idUsuarioRemetente, mensagem)

              binding.editTextMensagem.setText("")

          }

      }

    }

    private fun salvarMensagemFireStore(idUsuarioRemetente: String, idUsuarioDestinatario: String, mensagem: Mensagem) {
        firestore.collection(Constantes.MENSAGENS)
            .document(idUsuarioRemetente) // user logado e quem esta enviado a mensagem
            .collection(idUsuarioDestinatario) // destinatario e quem esta recebendo a mensagem
            .add(mensagem)
            .addOnFailureListener {
                exibirMensagens("Falha ao enviar mensagem")
            }
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