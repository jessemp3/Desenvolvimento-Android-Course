package com.jesse.threadsandcorotines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.threadsandcorotines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.lang.Thread.currentThread
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var pararThread = false
    private var job: Job? = null

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
            button3.setOnClickListener {
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
            }

            btnIniciar.setOnClickListener {
//                MinhaThread().start()
//                Thread(MinhaRunnable()).start()
                // outro metodo runnable usando lambda
//                Thread{
//                    repeat(30){ i ->
//                        Log.i("TAG", "Minha Thread: $i ${currentThread().name}")
//                        sleep(1000) // -> como aqui dentro eu ja henro de thread , só basta passar o sleep
//                        runOnUiThread { // -> para atualizar a interface
//                            binding.btnIniciar.text = "Contagem: $i"
//                            binding.btnIniciar.isEnabled = false // -> desabilita o botão
//
//                            if(i == 29){
//                                binding.btnIniciar.text = "Reiniciar"
//                                binding.btnIniciar.isEnabled = true
//                            }
//                        }
//                        /*
//                        * ui thread só deve ser usada pra att coisas na interface
//                        * */
//                    }
//                }.start()


             job = CoroutineScope(Dispatchers.IO).launch {
//                    recuperarUserLogado()
                 // executando com timeout
                 withTimeout(7000L) {
                     executar()
                 }
                }

            }

            btnparar.setOnClickListener {
                btnIniciar.text = "Reiniciar"
                btnIniciar.isEnabled = true
//                pararThread = true
                job?.cancel()
            }
        }
    }

//    override fun onStop() {
//        super.onStop()
//        job?.cancel()
//    }

    private suspend fun executar(){
        repeat(15) { i ->
            Log.i("Info_coroutine", "Minha Thread: $i ${currentThread().name}")

            withContext(Dispatchers.Main) {  // com esse trecho , eu consigo trocar o contexto da corotine pra mecher na ui , como nesse caso
                binding.btnIniciar.text = "Executou "
                binding.btnIniciar.isEnabled = false
            }

            delay(1000L)
        }
    }

    private suspend fun dadosUsuario() {
        /*
        * a questão principal aqui , é que o recuperar poderia estár pegando dados
        * de uma pai por exemplo
        * e esse dados podem demorar um teco
        * sem o suspend , o fluxo seguiria em frente sem esperar ter carregado
        * e o usuario ficar null , causando null exception e chashando o app
        * */
        val usuario = recuperarUserLogado()
        Log.d("TAG", "Usuario: $usuario T: ${currentThread().name}")
        // postagens cria uma dependencia no usuario
        val postgens = recuperarPostagens(usuario.id)
        Log.d("TAG", "Postagens: ${postgens.size}")
    }

    private suspend fun recuperarPostagens(id: Int): List<String> {
        delay(2000)
        return listOf("Postagem 1", "Postagem 2")
    }

    private suspend fun recuperarUserLogado(): User {
        delay(2000) // uma suspend fuction , não pode ser executada fora de uma coroutine
        return User(1, "Jesse")
    }

    inner class MinhaThread : Thread() {
        override fun run() {
            super.run()

            repeat(30) { i ->
                Log.i("TAG", "Minha Thread: $i ${currentThread().name}")
                sleep(1000) // -> como aqui dentro eu ja henro de thread , só basta passar o sleep
                runOnUiThread { // -> para atualizar a interface
                    binding.btnIniciar.text = "Contagem: $i"
                    binding.btnIniciar.isEnabled = false // -> desabilita o botão

                    if (i == 29) {
                        binding.btnIniciar.text = "Reiniciar"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                /*
                * ui thread só deve ser usada pra att coisas na interface
                * */
            }
        }
    }

    inner class MinhaRunnable : Runnable {
        override fun run() {
            repeat(30) { i ->

                if (pararThread) {
                    pararThread = false
                    return // isso retorna vazio pra função , que faz ela para
                }

                Log.i("TAG", "Minha Thread: $i ${currentThread().name}")
                sleep(1000) // -> como aqui dentro eu ja henro de thread , só basta passar o sleep
                runOnUiThread { // -> para atualizar a interface
                    binding.btnIniciar.text = "Contagem: $i"
                    binding.btnIniciar.isEnabled = false // -> desabilita o botão

                    if (i == 29) {
                        binding.btnIniciar.text = "Reiniciar"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                /*
                * ui thread só deve ser usada pra att coisas na interface
                * */
            }
        }

    }


}