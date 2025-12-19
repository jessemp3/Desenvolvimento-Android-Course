package com.jesse.sqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context): SQLiteOpenHelper(
    /*
    * 1 - contexto
    * 2 - nome do banco de dados ( não é necessario o .db , mas é bom )
    * 3 - factory (classe que da acesso aos registro que são retornados de uma tabela)
    * 4 - versão do banco de dados
    * */
    context, "loja.db", null , 1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not EXISTS produtos (" +
                "id_produto integer not NULL PRIMARY key AUTOINCREMENT," +
                "titulo varchar(100)," +
                "descricao text" +
                ");"


        try {
            db?.execSQL(sql)
            Log.i("info_db", "Tabela criada com sucesso")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela")
        }

    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        Log.i("info_db", "onUpgrade")

    }

}