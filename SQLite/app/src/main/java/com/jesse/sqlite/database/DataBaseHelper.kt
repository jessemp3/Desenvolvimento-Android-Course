package com.jesse.sqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(
    /*
    * 1 - contexto
    * 2 - nome do banco de dados ( não é necessario o .db , mas é bom )
    * 3 - factory (classe que da acesso aos registro que são retornados de uma tabela)
    * 4 - versão do banco de dados
    * */
    context, "loja.db", null , 1
) {
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        TODO("Not yet implemented")
    }

}