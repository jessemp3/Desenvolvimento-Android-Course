package com.jesse.sqlite.database

import android.content.Context
import android.util.Log
import com.jesse.sqlite.model.Produto

class ProdutoDAO(context: Context): IProdutoDAO {
    val escrita = DataBaseHelper(context).writableDatabase
    val leitura = DataBaseHelper(context).readableDatabase


    override fun salvar(produto: Produto): Boolean {
        val titulo = produto.titulo
        val sql =
            "insert into ${DataBaseHelper.TABELA_PRODUTOS}" +
                    " values (null , '$titulo' , 'Mackbook é bom pra trabalhar com kmp');"


        try {
            escrita.execSQL(sql)
            Log.i("info_db", "Produto salvo com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun atualizar(produto: Produto): Boolean {
        // update sem where atualiza tudo kkkk
        val titulo = produto.titulo
        val sql =
            "update ${DataBaseHelper.TABELA_PRODUTOS} " +
                    "set ${DataBaseHelper.TITULO} = '$titulo'" +
                    " WHERE ${DataBaseHelper.ID_PRODUTO} = 1;"


        try {
            escrita.execSQL(sql)
            Log.i("info_db", "Produto atualizado com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun remover(idProduto: Int): Boolean {
        val sql = "delete from ${DataBaseHelper.TABELA_PRODUTOS} " +
                "where ${DataBaseHelper.ID_PRODUTO} = $idProduto;"

        try {
            escrita.execSQL(sql)
            Log.i("info_db", "Produto removido com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun listar(): List<Produto> {
        val listaProdutos = mutableListOf<Produto>()

        val sql = "select * from ${DataBaseHelper.TABELA_PRODUTOS};"

        val cursor = leitura
            .rawQuery(sql, null)

        /*
        * dessa forma aqui , vc não precisa saber o indice da coluna
        * apenas passa o nome da coluna e o curos recupera o indice
        * */

        val indiceId = cursor.getColumnIndex(DataBaseHelper.ID_PRODUTO)
        val indiceTitulo = cursor.getColumnIndex(DataBaseHelper.TITULO)
        val indiceDescricao = cursor.getColumnIndex(DataBaseHelper.DESCRICAO)

        while (cursor.moveToNext()) { // true or false
            val idProduto = cursor.getInt(indiceId) // 0 -> posição da coluna id
            val titulo = cursor.getString(indiceTitulo) // 1 -> posição da coluna titulo
            val descricao = cursor.getString(indiceDescricao) // 2 -> posição da coluna descricao

            listaProdutos.add(
                Produto(idProduto, titulo, descricao)
            )
        }
        return listaProdutos
    }


}