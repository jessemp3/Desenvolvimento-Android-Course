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
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import java.lang.Thread.currentThread
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var pararThread = false

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


                CoroutineScope(Dispatchers.Main).launch {

                    binding.btnIniciar.text = "Executou "
                    repeat(15) { i ->
                        Log.i("Info_coroutine", "Minha Thread: $i ${currentThread().name}")
                        sleep(1000)
                    }
                }

            }

            btnparar.setOnClickListener {
                btnIniciar.text = "Reiniciar"
                btnIniciar.isEnabled = true
                pararThread = true
            }
        }
    }

    inner class MinhaThread: Thread(){
        override fun run() {
            super.run()

            repeat(30){ i ->
                Log.i("TAG", "Minha Thread: $i ${currentThread().name}")
                sleep(1000) // -> como aqui dentro eu ja henro de thread , só basta passar o sleep
                runOnUiThread { // -> para atualizar a interface
                    binding.btnIniciar.text = "Contagem: $i"
                    binding.btnIniciar.isEnabled = false // -> desabilita o botão

                    if(i == 29){
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

    inner class MinhaRunnable: Runnable{
        override fun run() {
            repeat(30){ i ->

                if(pararThread) {
                    pararThread = false
                     return // isso retorna vazio pra função , que faz ela para
                }

                Log.i("TAG", "Minha Thread: $i ${currentThread().name}")
                sleep(1000) // -> como aqui dentro eu ja henro de thread , só basta passar o sleep
                runOnUiThread { // -> para atualizar a interface
                    binding.btnIniciar.text = "Contagem: $i"
                    binding.btnIniciar.isEnabled = false // -> desabilita o botão

                    if(i == 29){
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