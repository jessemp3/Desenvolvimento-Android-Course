package com.jesse.whatsapp.activitys

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWebException
import com.google.firebase.firestore.FirebaseFirestore
import com.jesse.whatsapp.R
import com.jesse.whatsapp.databinding.ActivityCadastroBinding
import com.jesse.whatsapp.model.Usuario
import com.jesse.whatsapp.util.exibirMensagens
import com.jesse.whatsapp.util.setup

class CadastroActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }


    private lateinit var nome: String
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
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = getColor(R.color.primaria)

        binding.toolbar.setup(
            title = "Faça o seu Cadastro",
            onBack = {
                finish()
            }
        )

        initClicks()
    }

    private fun initClicks() {
        binding.btnCadastrar.setOnClickListener {
            if (validarCampos()) {
                cadastraUser(nome, email, senha)
            }
        }
    }

    private fun cadastraUser(nome: String, email: String, senha: String) {
        firebaseAuth.createUserWithEmailAndPassword(
            email, senha
        ).addOnCompleteListener { resultado ->
            if (resultado.isSuccessful) {

                //salvar os dados id , nome , email , foto
                val idUsuario = resultado.result.user?.uid
                if (idUsuario != null) {
                    val usuario = Usuario(
                        idUsuario, nome, email
                    )

                    salvarUsuarioFireStore(usuario)
                }
            }
        }.addOnFailureListener { exception ->
            try {
                throw exception

            } catch (erroSenhaFraca: FirebaseAuthWebException) {
                exibirMensagens("Senha Fraca, digite umam senha com letra e números")
                erroSenhaFraca.printStackTrace()
            } catch (erroCredencialInvalida: FirebaseAuthInvalidCredentialsException) {
                exibirMensagens("Digite um email válido")
                erroCredencialInvalida.printStackTrace()
            } catch (erroUserExistente: FirebaseAuthUserCollisionException) {
                exibirMensagens("Este email já está cadastrado")
                erroUserExistente.printStackTrace()
            }
        }
    }

    private fun salvarUsuarioFireStore(usuario: Usuario) {
        firestore
            .collection("usuarios")
            .document(usuario.id)
            .set(usuario)
            .addOnSuccessListener {
                exibirMensagens("Cadastro realizado com sucesso")
                startActivity(
                    Intent(applicationContext, MainActivity::class.java)
                )
            }.addOnFailureListener { exception ->
                exibirMensagens("Erro ao fazer cadastro")
                exception.printStackTrace()
            }

    }

    private fun validarCampos(): Boolean {
        nome = binding.editTextNomeCadastro.text.toString()
        email = binding.editTextEmailCadastro.text.toString()
        senha = binding.editTextPasswordCadastro.text.toString()


        if (nome.isNotEmpty()) {
            binding.tILNomeCadastro.error = null

            if (email.isNotEmpty()) {
                binding.tILEmailCadastro.error = null

                if (senha.isNotEmpty()) {
                    binding.tILPasswordCadastro.error = null
                    return true
                } else {
                    binding.tILPasswordCadastro.error = "Digite sua senha"
                    return false
                }

            } else {
                binding.tILEmailCadastro.error = "Digite seu email"
                return false
            }


        } else {
            binding.tILNomeCadastro.error = "Digite seu nome"
            return false
        }


    }
}