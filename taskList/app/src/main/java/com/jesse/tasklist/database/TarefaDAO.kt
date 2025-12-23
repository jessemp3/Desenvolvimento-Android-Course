package com.jesse.tasklist.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.jesse.tasklist.model.Tarefa

class TarefaDAO(context: Context) : ITarefaDAO {
    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase


    override fun salvar(tarefa: Tarefa): Boolean {

        val values = ContentValues()
        values.put(DatabaseHelper.COLUNA_DESCRICAO, tarefa.descricao)


        try {
            escrita.insert(
                DatabaseHelper.TABELA_TAREFAS,
                null,
                values
            )
            Log.d("DatabaseHelper", "Tarefa salva com sucesso")
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Erro ao salvar tarefa" )
            e.printStackTrace()
            return false
        }

        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun remover(idTarefa: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
       val listaTarefas = mutableListOf<Tarefa>()

        val sql = "Select * from ${DatabaseHelper.TABELA_TAREFAS}"


        return  listaTarefas
    }
}