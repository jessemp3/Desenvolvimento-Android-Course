package com.jesse.tasklist.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jesse.tasklist.R
import com.jesse.tasklist.adapter.TarefaAdapter
import com.jesse.tasklist.database.TarefaDAO
import com.jesse.tasklist.databinding.ActivityMainBinding
import com.jesse.tasklist.model.Tarefa

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas = emptyList<Tarefa>()
    private var tarefaAdapter:TarefaAdapter? = null

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
            fabAdicionar.setOnClickListener {
                val intent = Intent(this@MainActivity, AdicionarTarefaActivity::class.java)
                startActivity(intent)
            }

            // RecyclerView
            tarefaAdapter = TarefaAdapter(
                {id -> confimarExclucao(id)}
            )
            rvTarefas.adapter = tarefaAdapter

            rvTarefas.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onStart() {
        super.onStart()
        atulizarLista()
    }

    private fun atulizarLista(){
        listaTarefas = TarefaDAO(this@MainActivity).listar()
        tarefaAdapter?.adicionarLista(listaTarefas)
    }

    private fun ActivityMainBinding.confimarExclucao(id: Int) {
        val alertBuilder = AlertDialog.Builder(this@MainActivity)

        alertBuilder.setTitle("Confirmar Exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")

        alertBuilder.setPositiveButton("Sim") { _, _ ->
            val tarefaDao = TarefaDAO(this@MainActivity)

            if(tarefaDao.remover(id)){
                Toast.makeText(this@MainActivity, "Tarefa removida com sucesso", Toast.LENGTH_SHORT).show()
                atulizarLista()
            }else{
                Toast.makeText(this@MainActivity, "Erro ao remover tarefa", Toast.LENGTH_SHORT).show()
            }
        }
        alertBuilder.setNegativeButton("Não") { _, _ -> }

        alertBuilder.create().show()
    }

}

