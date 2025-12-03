package com.jesse.listasecoleoces.reciclerView

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jesse.listasecoleoces.R
import com.jesse.listasecoleoces.databinding.ActivityRecyclerBinding
import com.jesse.listasecoleoces.reciclerView.repository.Mensagem

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerBinding
    private lateinit var mensagemAdapter: MensagemAdapter

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

        val lista = mutableListOf( // conjunto de dados -> dataset
          Mensagem(R.drawable.perfil, "kaique", "Olá, como vai?", "08:15"),
          Mensagem(R.drawable.perfil, "jesse", "Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?Vamos almoçar?", "09:30"),
          Mensagem(R.drawable.perfil, "cice", "Encontro às 14h", "11:45"),
          Mensagem(R.drawable.perfil, "alice", "Obrigada!", "13:05"),
          Mensagem(R.drawable.perfil, "mariana", "Vou chegar tarde", "15:20"),
          Mensagem(R.drawable.perfil, "pedro", "Beleza", "16:00"),
          Mensagem(R.drawable.perfil, "lucas", "Confirmação enviada", "17:10"),
          Mensagem(R.drawable.perfil, "ana", "Top", "18:25"),
          Mensagem(R.drawable.perfil, "bruno", "Pronto", "19:40"),
          Mensagem(R.drawable.perfil, "sofia", "racaoo", "20:55")
        )

        mensagemAdapter =  MensagemAdapter{ nome ->
            Toast.makeText(this, nome, Toast.LENGTH_SHORT).show()
        }
        mensagemAdapter.atualizarListaDados(
            lista
        )
        binding.recyclerView.adapter = mensagemAdapter

    // possivel fazer essa config via xml ou por aqui (code)
        binding.recyclerView.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)

        // criação de um divisor
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this , DividerItemDecoration.VERTICAL))


        binding.btnFlutuante.setOnClickListener {
            lista.add(
                Log.d("RecyclerView", "Clicou no botão"),
                Mensagem(R.drawable.perfil, "amoour", "ahhhh araa", "19:03")
            )
            Log.d("RecyclerView", lista.toString())
            mensagemAdapter.atualizarListaDados(lista)
        }

//        binding.recyclerView.layoutManager = GridLayoutManager(this , 2)
//        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
//        binding.recyclerView.clipToPadding = false
    }
}