package com.jesse.tasklist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.tasklist.R
import com.jesse.tasklist.database.TarefaDAO
import com.jesse.tasklist.databinding.ActivityAdicionarTarefaBinding
import com.jesse.tasklist.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // recuperar tarefas passada
        var tarefa: Tarefa? = null
        val bundle = intent.extras
        if (bundle != null) {
            tarefa = bundle.getParcelable<Tarefa>("tarefa") as Tarefa

            binding.textViewTitle.text = "Editar Tarefa"
            binding.btnSalvar.text = "Editar"
            binding.editTextTextTarefa.setText(tarefa.descricao)
        }

        with(binding) {
            btnSalvar.setOnClickListener {
                if (editTextTextTarefa.text.isNotEmpty()) {
                    if (tarefa != null) {
                        editar(tarefa)
                    } else {
                        salvar()
                    }

                } else {
                    Toast.makeText(
                        this@AdicionarTarefaActivity,
                        "Preencha uma tarefa",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun ActivityAdicionarTarefaBinding.salvar() {
        val descricao = editTextTextTarefa.text.toString()
        val tarefa = Tarefa(
            -1,
            descricao,
            "default"
        )

        val tarefaDAO = TarefaDAO(this@AdicionarTarefaActivity)
        if (tarefaDAO.salvar(tarefa)) {
            Toast.makeText(
                this@AdicionarTarefaActivity,
                "Tarefa salva com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun ActivityAdicionarTarefaBinding.editar(tarefa: Tarefa) {
        textViewTitle.text = "Editar Tarefa"
        val descricao = editTextTextTarefa.text.toString()

        val tarefaAtualizar = Tarefa(
            tarefa.idTarefa,
            descricao,
            "default"
        )

        val tarefaDAO = TarefaDAO(this@AdicionarTarefaActivity)

        if (tarefaDAO.atualizar(tarefaAtualizar)) {
            Toast.makeText(
                this@AdicionarTarefaActivity,
                "Tarefa editada com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

}

