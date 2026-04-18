package com.jesse.whatsapp.activitys

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.jesse.whatsapp.adapters.MensagensAdapter
import com.jesse.whatsapp.databinding.ActivityMensagensBinding
import com.jesse.whatsapp.model.Conversa
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
    private var dadosUsuarioRementente: Usuario? = null
    private lateinit var snapshotListener: ListenerRegistration
    private lateinit var conversasAdapter: MensagensAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recuperarDadosUsuarios()
        iniciarlizarToolbar()
        initClicks()
        inicializarRecyclerview()
        inicializarListeners()
    }

    private fun inicializarRecyclerview() {

        with(binding) {
            conversasAdapter = MensagensAdapter()
            recyclerViewMensagens.adapter = conversasAdapter
            recyclerViewMensagens.layoutManager = LinearLayoutManager(applicationContext)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        snapshotListener.remove()
    }

    private fun inicializarListeners() {
        val idUsuarioRemetente = firebaseAuth.currentUser?.uid
        val idUsuarioDestinatario = dadosDestinatario?.id

        if (idUsuarioRemetente != null && idUsuarioDestinatario != null) {
            snapshotListener = firestore.collection(Constantes.MENSAGENS)
                .document(idUsuarioRemetente)
                .collection(idUsuarioDestinatario)
                .orderBy("data", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshots, exception ->
                    if (exception != null) {
                        exibirMensagens("Erro ao carregar mensagens")
                        return@addSnapshotListener
                    }

                    val listaMensagens = mutableListOf<Mensagem>()
                    val documentos = snapshots?.documents

                    documentos?.forEach { documento ->
                        val mensagem = documento.toObject(Mensagem::class.java)

                        if (mensagem != null) {
                            listaMensagens.add(mensagem)
                            Log.d("TAG", "inicializarListeners: ${mensagem.mensagem}")
                        }
                    }

                    // lista
                    if (listaMensagens.isNotEmpty()) {
                        conversasAdapter.adicionarLista(listaMensagens)
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

    private fun salvarMensagem(textoMensagem: String) {
        if (textoMensagem.isNotEmpty()) {

            val idUsuarioRemetente = firebaseAuth.currentUser?.uid
            val idUsuarioDestinatario = dadosDestinatario?.id
            if (idUsuarioRemetente != null && idUsuarioDestinatario != null) {
                val mensagem = Mensagem(
                    idUsuarioRemetente, textoMensagem
                )

                //Salvar para o Remetente
                salvarMensagemFirestore(
                    idUsuarioRemetente, idUsuarioDestinatario, mensagem
                )
                //Jamilton -> Foto e nome Destinatario (ana)
                val conversaRemetente = Conversa(
                    idUsuarioRemetente, idUsuarioDestinatario,
                    dadosDestinatario!!.foto, dadosDestinatario!!.nome,
                    textoMensagem
                )
                salvarConversaFirestore(conversaRemetente)

                //Salvar mesma mensagem para o destinatario
                salvarMensagemFirestore(
                    idUsuarioDestinatario, idUsuarioRemetente, mensagem
                )
                //Ana -> Foto e nome Remente (jamilton)
                val conversaDestinatario = Conversa(
                    idUsuarioDestinatario, idUsuarioRemetente,
                    dadosUsuarioRementente!!.foto, dadosUsuarioRementente!!.nome,
                    textoMensagem
                )
                salvarConversaFirestore(conversaDestinatario)

                binding.editTextMensagem.setText("")

            }

        }
    }

    private fun salvarConversaFirestore(conversa: Conversa) {
        firestore
            .collection(Constantes.CONVERSAS)
            .document(conversa.idUsuarioRementente)
            .collection(Constantes.ULTIMAS_CONVERSAS)
            .document(conversa.idUsuarioDestinatario)
            .set(conversa)
            .addOnFailureListener {
                exibirMensagens("Falha ao salvar conversa")
            }

    }


    private fun salvarMensagemFirestore(
        idUsuarioRemetente: String,
        idUsuarioDestinatario: String,
        mensagem: Mensagem
    ) {
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
            if (dadosDestinatario != null) {
                binding.textViewTitleNameMensagem.text = dadosDestinatario!!.nome
                Glide
                    .with(this@MensagensActivity)
                    .load(dadosDestinatario!!.foto?.toUri())
                    .into(binding.imageViewPerfilMensagem)
            }
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun recuperarDadosUsuarios() {

        //Dados do usuário logado
        val idUsuarioRemetente = firebaseAuth.currentUser?.uid
        if (idUsuarioRemetente != null) {
            firestore
                .collection(Constantes.USUARIOS)
                .document(idUsuarioRemetente)
                .get()
                .addOnSuccessListener { documentSnapshot ->

                    val usuario = documentSnapshot.toObject(Usuario::class.java)
                    if (usuario != null) {
                        dadosUsuarioRementente = usuario
                    }

                }
        }

        //Recuperando dados destinatário
        val extras = intent.extras
        if (extras != null) {

            val origem = extras.getString("origem")
            if (origem == Constantes.ORIGEM_CONTATO) {

                dadosDestinatario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    extras.getParcelable(
                        "dadosDestinatario",
                        Usuario::class.java
                    )
                } else {
                    extras.getParcelable(
                        "dadosDestinatario"
                    )
                }

            } else if (origem == Constantes.ORIGEM_CONVERSA) {
                //Recuperar os dados da conversa

            }

        }

    }
}