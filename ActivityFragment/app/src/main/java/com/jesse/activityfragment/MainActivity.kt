package com.jesse.activityfragment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.activityfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonAbrir.setOnClickListener {
            val intent = Intent(this , DetalhesActivity::class.java)

            val filme: Filme = Filme(
                nome = "Taxi Driver",
                description = "Um veterano da Guerra do Vietnã trabalha como motorista de táxi em Nova York." +
                        " Perturbado pela decadência moral que o cerca, ele planeja um ato violento para 'salvar' uma jovem prostituta.",
                avaliacoes = 8.3,
                diretor = "Martin Scorsese",
                distribuidora = "Columbia Pictures",
                anoLancamento = 1976
            )


            intent.putExtra("filme",filme)

            // passar parametro pra outra activity
            intent.putExtra("name", "Jesse")
            intent.putExtra("age", 20)

            startActivity(intent)
        }
    }
}
