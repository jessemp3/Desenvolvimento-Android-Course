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



        with(binding){
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

    private fun ActivityMainBinding.salvar(){
        val titulo = editTextText.text.toString()
        val sql = "insert into produtos values (null , '$titulo' , 'Mackbook é bom pra trabalhar com kmp');"

        try {
            bancoDeDados?.writableDatabase?.execSQL(sql)
            Log.i("info_db", "Produto salvo com sucesso")
            editTextText.text.clear()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


    private fun leitura(){
        try {

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun ActivityMainBinding.atualizar() {
        TODO("Not yet implemented")
    }

    private fun ActivityMainBinding.remover() {
        TODO("Not yet implemented")
    }

    private fun ActivityMainBinding.listar() {
        TODO("Not yet implemented")
    }


}



