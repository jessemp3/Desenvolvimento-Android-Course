package com.jesse.listasecoleoces.reciclerView

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jesse.listasecoleoces.R
import com.jesse.listasecoleoces.databinding.ActivityRecyclerBinding
import com.jesse.listasecoleoces.reciclerView.repository.Mensagem

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lista = listOf(
          Mensagem(R.drawable.perfil, "kaique", "Olá, como vai?", "08:15"),
          Mensagem(R.drawable.perfil, "jesse", "Vamos almoçar?", "09:30"),
          Mensagem(R.drawable.perfil, "cice", "Encontro às 14h", "11:45"),
          Mensagem(R.drawable.perfil, "alice", "Obrigada!", "13:05"),
          Mensagem(R.drawable.perfil, "mariana", "Vou chegar tarde", "15:20"),
          Mensagem(R.drawable.perfil, "pedro", "Beleza", "16:00"),
          Mensagem(R.drawable.perfil, "lucas", "Confirmação enviada", "17:10"),
          Mensagem(R.drawable.perfil, "ana", "Top", "18:25"),
          Mensagem(R.drawable.perfil, "bruno", "Pronto", "19:40"),
          Mensagem(R.drawable.perfil, "sofia", "racaoo", "20:55")
        )

        binding.recyclerView.adapter = MensagemAdapter(lista)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}