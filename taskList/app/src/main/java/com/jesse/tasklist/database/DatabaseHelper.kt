package com.jesse.tasklist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    NOME_BANCO_DADOS,
    null,
    VERSION
) {

    companion object {
        const val  NOME_BANCO_DADOS = "ListaTarefas.db"
        const val VERSION = 1

        const val TABELA_TAREFAS = "tarefas"
        const val COLUNA_ID_TAREFA = "id_tarefa"
        const val COLUNA_DESCRICAO = "descricao"
        const val COLUNA_DATA_CADASTRO = "data_cadastro"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not EXISTS $TABELA_TAREFAS(" +
                "$COLUNA_ID_TAREFA Integer not null PRIMARY key AUTOINCREMENT," +
                "$COLUNA_DESCRICAO varchar (60)," +
                "$COLUNA_DATA_CADASTRO datetime not null DEFAULT CURRENT_TIMESTAMP" +
                ");"

        try {
            db?.execSQL(sql)
            Log.d("DatabaseHelper", "Tabela criada com sucesso")
        }catch (e: Exception){
            Log.e("DatabaseHelper", "Erro ao criar a tabela", e)
            e.printStackTrace()
        }
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        TODO("Not yet implemented")
    }
}