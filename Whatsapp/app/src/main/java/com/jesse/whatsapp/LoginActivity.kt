package com.jesse.whatsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jesse.whatsapp.databinding.ActivityLoginBinding
import com.jesse.whatsapp.util.exibirMensagens

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var email: String
    private lateinit var senha: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initClicks()
        firebaseAuth.signOut()
    }

    override fun onStart() {
        super.onStart()
        verificarUsuarioLogado()
    }

    override fun onDestroy() {
        super.onDestroy()
//        firebaseAuth.signOut()
    }

    private fun verificarUsuarioLogado() {
        val userAtual = firebaseAuth.currentUser
        if(userAtual != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initClicks() {
        binding.textViewCadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
        binding.btnLogar.setOnClickListener {
            if (validarCampos()) {
                logarUsuario()
            }
        }
    }

    fun logarUsuario() {
        firebaseAuth.signInWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener {
            exibirMensagens("Logado com sucesso")
            startActivity(Intent(this, MainActivity::class.java))
        }.addOnFailureListener { error ->
            try {
                throw error
            } catch (erroUserInvalido: FirebaseAuthInvalidUserException) {
                exibirMensagens("Email não cadastrado !")
            }catch (erroCredencialInvalida: FirebaseAuthInvalidCredentialsException) {
                exibirMensagens("Email ou senha incorretos !")
            }
        }
    }

    fun validarCampos(): Boolean {
        email = binding.editTextEmailLogin.text.toString()
        senha = binding.editTextPasswordLogin.text.toString()

        if (email.isNotEmpty()) {
            binding.tILEmailLogin.error = null

            if (senha.isNotEmpty()) {
                binding.tILPasswordLogin.error = null
                return true
            } else {
                binding.tILPasswordLogin.error = "Informe a senha"
                return false
            }

        } else {
            binding.tILEmailLogin.error = "Informe o email"
            return false
        }

    }
}