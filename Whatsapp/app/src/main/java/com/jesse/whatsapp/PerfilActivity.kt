package com.jesse.whatsapp

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.common.io.Files.map
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.jesse.whatsapp.databinding.ActivityPerfilBinding
import com.jesse.whatsapp.util.exibirMensagens
import com.jesse.whatsapp.util.setup
import androidx.core.net.toUri
import com.bumptech.glide.Glide

class PerfilActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPerfilBinding.inflate(layoutInflater)
    }

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val storate by lazy {
        FirebaseStorage.getInstance()
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }



    private var temPermisaoGaleria = false
    private var temPermisaoCamera = false
    private val idUser = firebaseAuth.currentUser?.uid

    private val gerenciadorGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){uri ->
        if(uri != null){
            binding.imageViewPerfil.setImageURI(uri)
            uploadImageStorage(uri)
        }else{
           exibirMensagens("Erro ao selecionar imagem")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        solicitarPermisoes()
        initClicks()

        binding.includePerfilToolbar.setup(
            title = "Perfil",
            onBack = {
                finish()
            }
        )
    }

    override fun onStart() {
        super.onStart()
        recuperarDadosIniciais()
    }

    private fun recuperarDadosIniciais() {
        if(idUser == null) return

        firestore
            .collection("usuarios")
            .document(idUser)
            .get()
            .addOnSuccessListener {documentSnapshot ->
                val nome = documentSnapshot.getString("nome")
                val foto = documentSnapshot.getString("foto")

                if(nome != null){
                    binding.editTextNome.setText(nome)
                }
                if(foto != null){
                    Glide
                        .with(this)
                        .load(foto)
                        .into(binding.imageViewPerfil)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initClicks() {
        binding.fabAdd.setOnClickListener {
            if (temPermisaoGaleria) {
                gerenciadorGaleria.launch("image/*")
            }else{
                exibirMensagens("Não tem Permisão para acessar a galeria")
                solicitarPermisoes()
            }
        }

        binding.btnSalvar.setOnClickListener {
            val nomeUser = binding.editTextNome.text.toString()

            if(nomeUser.isNotEmpty()){
                if(idUser == null) return@setOnClickListener

                val dados = mapOf(
                    "nome" to nomeUser,
                )
                atualizarDadosPerfil(idUser , dados)
            }else{
                exibirMensagens("Digite um nome")
            }
        }
    }

    fun uploadImageStorage(uri: Uri) {
        if (idUser != null) {
            // fotos -> usuarisio -> iduser -> perfil.jpg
            storate.getReference("fotos")
                .child("usuarios")
                .child(idUser)
                .child("perfil.jpg")
                .putFile(uri)
                .addOnSuccessListener { task ->
                    exibirMensagens("Sucesso ao fazer upload da imagem")
                    task.metadata
                        ?.reference
                        ?.downloadUrl
                        ?.addOnSuccessListener { uri ->
                            val dados = mapOf(
                                "foto" to uri.toString(),
                            )
                            atualizarDadosPerfil(idUser , dados )
                        }
                        ?.addOnFailureListener {  }


                }.addOnFailureListener {
                    exibirMensagens("Erro ao fazer upload da imagem")
                }
        }
    }

    fun atualizarDadosPerfil(idUser: String, dados: Map<String, String>) {
        firestore.collection("usuarios")
            .document(idUser)
            .update(dados)
            .addOnSuccessListener {
                exibirMensagens("Dados do perfil atualizado com sucesso")
            }
            .addOnFailureListener {
                exibirMensagens("Erro ao atualizar os dados do perfil")
            }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun solicitarPermisoes() {
        //verificar se já tem a permisao
        temPermisaoCamera = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        temPermisaoGaleria = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED


        // lista de negadas
        val listaPermisoesNegadas = mutableListOf<String>()
        if(!temPermisaoCamera ){
            listaPermisoesNegadas.add(android.Manifest.permission.CAMERA)
        }
        if(!temPermisaoGaleria){
            listaPermisoesNegadas.add(android.Manifest.permission.READ_MEDIA_IMAGES)
        }

        if(listaPermisoesNegadas.isNotEmpty()){
            //solicitar multiplicar permisoes
            val gerenciadorPermisoes = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ){permisoes ->
                temPermisaoCamera = permisoes[android.Manifest.permission.CAMERA] ?: temPermisaoCamera
                temPermisaoGaleria = permisoes[android.Manifest.permission.READ_MEDIA_IMAGES] ?: temPermisaoGaleria
            }

            gerenciadorPermisoes.launch(listaPermisoesNegadas.toTypedArray())
        }



    }
}