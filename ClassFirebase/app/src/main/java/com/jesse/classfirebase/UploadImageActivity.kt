package com.jesse.classfirebase

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.jesse.classfirebase.databinding.ActivityUploadImageBinding
import java.util.UUID

class UploadImageActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUploadImageBinding.inflate(layoutInflater)
    }

    private val storate by lazy{
        FirebaseStorage.getInstance()
    }

    private val auth by lazy{
        FirebaseAuth.getInstance()
    }


    private var uriImageSelecionada: Uri? = null
    private val abrirGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){uri -> 
        if(uri != null) {

            uriImageSelecionada = uri


            Glide.with(this)
                .load(uri)
                .centerCrop()
                .into(binding.imageView)


            Toast.makeText(this, "Imagem selecionada", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Nenhuma imagem selecioanda", Toast.LENGTH_SHORT).show()
        }
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
            imageBtnInsert.setOnClickListener {
                Log.d("teste", "teste")
                abrirGaleria.launch("image/*")//mime type
            }

            fabUpload.setOnClickListener {
                uploadGaleria()
            }

            fabRecuperar.setOnClickListener {
                recuperarImageFirebase()
            }
        }
    }

    fun recuperarImageFirebase() {
        val userId = auth.currentUser?.uid

        if(userId != null){
            storate.getReference("fotos")
                .child(userId)
                .child("fotoPerfil.jpg")
                .downloadUrl
                .addOnSuccessListener { url ->
                    Glide.with(this)
                        .load(url)
                        .centerCrop()
                        .into(binding.imageView)
                }
        }

    }

    fun uploadGaleria() {
        // fotos -> viagens -> arquivo

        /*
        * forma correta
        * fotos ≥ id_user_logado ≥ identicador(se eu gerar cada foto com o mesmo nome, ele vai sobrescrever a foto antiga)
        * */

        val userId = auth.currentUser?.uid
        val nomeImage = UUID.randomUUID().toString() // forma de gerar uuid unico

        Log.d("userId" , "$userId")

        if(uriImageSelecionada != null && userId != null){
            // dessa forma eu estou criando a pasta fotos e dentro dela a pasta aula e só ai o arquivo foto
            storate.getReference("fotos")
                .child(userId)
                .child("fotoPerfil.jpg") // nesse caso , vou usar valor fixo , mas o certo é usar um valor dinamico
                .putFile(uriImageSelecionada!!)
                .addOnSuccessListener {
                    Toast.makeText(this, "Upload realizado com sucesso", Toast.LENGTH_SHORT).show()

                    it.metadata?.reference?.downloadUrl?.addOnSuccessListener { urlFirebase ->
                        Toast.makeText(this, "$urlFirebase", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { error ->
                    error.printStackTrace()
                    Toast.makeText(this, "Erro ao fazer upload", Toast.LENGTH_SHORT).show()
                }
        }


    }
}