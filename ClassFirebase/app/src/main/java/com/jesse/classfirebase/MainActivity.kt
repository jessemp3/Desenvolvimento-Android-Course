package com.jesse.classfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jesse.classfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val auth = FirebaseAuth.getInstance()
    private val banco = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnCadastar.setOnClickListener {
            criarUser()
        }

        binding.btnLogar.setOnClickListener {
            logarUser()
        }

        binding.btnDeslogar.setOnClickListener {
            auth.signOut() // metodo de deslogar o user
        }

        binding.btnSalvar.setOnClickListener {
            salvarDados()
        }

        binding.btnAtt.setOnClickListener {
            attDados()
        }

        binding.btnListar.setOnClickListener {
            listarDados()
        }

        binding.btnPesquisar.setOnClickListener {
            pesquisarDados()
        }

    }



    override fun onStart() {
        super.onStart()
//        verificarUserLogado()
    }


    fun pesquisarDados() {
        // aplicando filtros pra pesquisa
        val ref = banco.collection("users")
//            .whereEqualTo("nome", "jesse") // onde o atributo nome for igual a jesse
//           .whereNotEqualTo("idade", 21) -> onde o atributo idade for diferente de 21
//            .whereIn("nome" , listOf("jesse" , "kaiqueeee")) // onde o atributo nome for igual a jesse ou kaique
//            .whereNotIn("nome", listOf("jesse", "kaique")) // onde o atributo nome for diferente de jesse ou kaique
//            .whereArrayContainsAny("skills" , listOf("java" , "kotlin" , "hilt"))


            // pesquisas com > , >= , < , <= , == , !=

//            .whereGreaterThan("idade" , 40)
//            .whereGreaterThanOrEqualTo("idade" , 21)
//            .whereLessThan("idade" , 114)
            .whereLessThanOrEqualTo("idade" , 21)

        ref.addSnapshotListener { snapshot, exception ->
//                val dados = snapshot?.data
            val dadosDocuments = snapshot?.documents
            var lista = "" // dessa forma eu recupero tudo que tem no banco
            dadosDocuments?.forEach {
                val dados = it.data

                if (dados != null) {
                    val nome = dados.get("nome")
                    val idade = dados.get("idade")

                    lista += "Nome: $nome \nIdade: $idade \n\n"

                }

            }

            binding.textViewResultado.text = lista

            if (exception != null) {
                exibirMensagem("Erro ao listar dados")
            }

        }
    }


    fun listarDados() {
//        salvarDadosUser("kaique", 21)
        val idUserLogao = auth.currentUser?.uid
        if (idUserLogao != null) {
            val ref = banco.collection("users")
//                .document(idUserLogao)

            // dessa forma eu pego os dados , mas ele att eu preciso fazer outra chamada pra saber
            /*
            ref.get()
                .addOnSuccessListener { snapshot ->
                    val dados = snapshot.data

                    if(dados != null){
                        val nome = dados.get("nome")
                        val idade = dados.get("idade")

                        binding.textViewResultado.text = "Nome: $nome \nIdade: $idade"

                    }

                }.addOnFailureListener {
                    exibirMensagem("Erro ao listar dados")
                }

             */

            // dessa forma quaquer att no banco , eu vou receber instantaneamente
            ref.addSnapshotListener { snapshot, exception ->
//                val dados = snapshot?.data
                val dadosDocuments = snapshot?.documents
                var lista = "" // dessa forma eu recupero tudo que tem no banco
                dadosDocuments?.forEach {
                    val dados = it.data

                    if (dados != null) {
                        val nome = dados.get("nome")
                        val idade = dados.get("idade")

                        lista += "Nome: $nome \nIdade: $idade \n\n"

                    }

                }

                binding.textViewResultado.text = lista

//
//                if(dados != null){
//                    val nome = dados.get("nome")
//                    val idade = dados.get("idade")
//
//                    binding.textViewResultado.text = "Nome: $nome \nIdade: $idade"
//
//                }

                if (exception != null) {
                    exibirMensagem("Erro ao listar dados")
                }

            }


        }

    }

    fun salvarDadosUser(nome: String, idade: Int) {


        val idUserLogado = auth.currentUser?.uid
        if (idUserLogado != null) {

            val dados = mapOf(
                "nome" to nome,
                "idade" to idade
            )


            banco.collection("users")
                .document(idUserLogado)
                .set(
                    dados
                )
        }
    }

    fun salvarDados() {
        banco.collection("users")
            .document("3")
            .set(
                hashMapOf(
                    "nome" to "jesse",
                    "idade" to 21,
                    "cpf" to "123456789"
                )
            )
            .addOnSuccessListener {
                exibirMensagem("Dados salvos com sucesso")
            }.addOnFailureListener { exeption ->
                exeption.printStackTrace()
                exibirMensagem("Erro ao salvar dados")
            }
    }

    fun attDados() {
        val idUser = auth.currentUser?.uid
        if (idUser != null) { // dessa forma eu crio um document em relação ao use que esta logado


        }

        val ref = banco.collection("users")
            .add(
                hashMapOf(
                    "nome" to "kaique",
                )
            )


        /*ref.set(
            hashMapOf(
                "nome" to "kaique",
            )
        ) */ // o set substitui todos os dados do documento


//        ref.update("nome" , "jesse" )
////            .addOnSuccessListener {
////                exibirMensagem("Dados atualizados com sucesso")
////            }.addOnFailureListener { exception ->
////                exception.printStackTrace()
////                exibirMensagem("Erro ao atualizar dados")
////            }


//        ref.delete() // nesse caso ele delete o ducument inteiro , que nesse caso é o 2
//            .addOnSuccessListener {
//                exibirMensagem("Dados deletados com sucesso")
//            }.addOnFailureListener { exception ->
//                exception.printStackTrace()
//                exibirMensagem("Erro ao deletar dados")
//            }


    }

    //fazendo cadastro de user via email e senha
    fun criarUser() {
        // user digitou dados e coletei
        val email = "jesse.teste@gmail.com"
        val senha = "Tururu12.@!"

        // realizando o cadastro
        // dar preferencia em usar as instancias exatamente do q vc precisa e não só chamar o firebase
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { resultado ->
            val emailUser = auth.currentUser?.email
            val idUser = auth.currentUser?.uid

            salvarDadosUser("jesse", 21)


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

    fun logarUser() {
        val email = "jesse.teste@gmail.com"
        val senha = "Tururu12.@!"

        auth.signInWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { result ->
            val id = result.user?.uid

            binding.textViewResultado.text = "Logado com sucesso"
            exibirMensagem("Logado com sucesso")

        }.addOnFailureListener { exception ->
            val erro = exception.printStackTrace()
            binding.textViewResultado.text = "Erro ao logar ${exception}"
            exibirMensagem("Erro ao logar usuário ${erro}")
        }
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
        } else {
            exibirMensagem("Usuário não logado")
        }

    }
}