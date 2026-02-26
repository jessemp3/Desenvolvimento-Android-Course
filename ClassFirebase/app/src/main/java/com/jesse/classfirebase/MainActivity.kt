package com.jesse.classfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.jesse.classfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnExecutar.setOnClickListener {
            criarUser()
        }

    }

    override fun onStart() {
        super.onStart()

        verificarUserLogado()
    }


    // verificação se o user já tem cadastro
    fun verificarUserLogado() {
        val userAtual = auth.currentUser
        val userid = userAtual?.uid

        if (userAtual != null) {
            exibirMensagem("Usuário logado ${userid}")
            startActivity(
                Intent(this, PrincipalActivity::class.java)
            )
        }else{
            exibirMensagem("Usuário não logado")
        }

    }


    //fazendo cadastro de user via email e senha
    fun criarUser(){
        // user digitou dados e coletei
        val email = "kaique.teste@gmail.com"
        val senha = "Tururu12.@!"

        // realizando o cadastro
        // dar preferencia em usar as instancias exatamente do q vc precisa e não só chamar o firebase
       val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(
            email , senha
        ).addOnSuccessListener { resultado ->
            val emailUser = auth.currentUser?.email
            val idUser = auth.currentUser?.uid

            exibirMensagem("Usuário criado com sucesso ${idUser}")
            binding.textViewResultado.text = "User: $emailUser"
        }.addOnFailureListener { exception ->
            val erro = exception.printStackTrace()
            exibirMensagem("Erro ao criar usuário ${erro}")
        }
    }

    fun exibirMensagem(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}