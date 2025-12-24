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

        val sql = "SELECT " +
                "${DatabaseHelper.COLUNA_ID_TAREFA}, ${DatabaseHelper.COLUNA_DESCRICAO}," +
                "strftime('%d/%m/%Y %H:%M' , ${DatabaseHelper.COLUNA_DATA_CADASTRO}) ${DatabaseHelper.COLUNA_DATA_CADASTRO}  " +
                "from ${DatabaseHelper.TABELA_TAREFAS};"

        val cursor = leitura.rawQuery(sql , null)

        val indiceId = cursor.getColumnIndex(DatabaseHelper.COLUNA_ID_TAREFA)
        val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.COLUNA_DESCRICAO)
        val indiceDATA = cursor.getColumnIndex(DatabaseHelper.COLUNA_DATA_CADASTRO)

        while(cursor.moveToNext()){
            val idTarefa = cursor.getInt(indiceId)
            val descricao = cursor.getString(indiceDescricao)
            val data = cursor.getString(indiceDATA)

            listaTarefas.add(
                Tarefa(idTarefa, descricao, data)
            )
        }

        return  listaTarefas
    }
}