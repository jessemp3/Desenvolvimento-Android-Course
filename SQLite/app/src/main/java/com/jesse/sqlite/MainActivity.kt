package com.jesse.sqlite

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.sqlite.database.DataBaseHelper
import com.jesse.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    private val bancoDeDados by lazy {
        DataBaseHelper(this)
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



        with(binding) {
            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListar.setOnClickListener {
                listar()
            }

            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                remover()
            }
        }

    }

    private fun ActivityMainBinding.salvar() {
        val titulo = editTextText.text.toString()
        val sql =
            "insert into ${DataBaseHelper.TABELA_PRODUTOS} values (null , '$titulo' , 'Mackbook é bom pra trabalhar com kmp');"

        try {
            bancoDeDados.writableDatabase?.execSQL(sql)
            Log.i("info_db", "Produto salvo com sucesso")
            editTextText.text.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun ActivityMainBinding.atualizar() {
        val titulo = editTextText.text.toString()
        // update sem where atualiza tudo kkkk
        val sql =
            "update ${DataBaseHelper.TABELA_PRODUTOS} " +
                    "set ${DataBaseHelper.TITULO} = '$titulo'" +
                    " WHERE ${DataBaseHelper.ID_PRODUTO} = 1;"


        try {
            bancoDeDados.writableDatabase.execSQL(sql)
            Log.i("info_db", "Produto atualizado com sucesso")
            editTextText.text.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun ActivityMainBinding.remover() {
        val sql = "delete from ${DataBaseHelper.TABELA_PRODUTOS} where ${DataBaseHelper.ID_PRODUTO} = 1;"

        try {
            bancoDeDados.writableDatabase?.execSQL(sql)
            Log.i("info_db", "Produto removido com sucesso")
            editTextText.text.clear()
        } catch (e: Exception) {
        }
    }

    private fun ActivityMainBinding.listar() {
        val sql = "select * from ${DataBaseHelper.TABELA_PRODUTOS};"

        val cursor = bancoDeDados.readableDatabase
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


            Log.d("info_db", "id: $idProduto , titulo: $titulo , descricao: $descricao")
            Log.d("info_db", "cursor: ${cursor.position}")
        }
    }
}




