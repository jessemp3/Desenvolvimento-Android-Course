package com.jesse.sqlite

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.sqlite.database.DataBaseHelper
import com.jesse.sqlite.database.ProdutoDAO
import com.jesse.sqlite.databinding.ActivityMainBinding
import com.jesse.sqlite.model.Produto

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

        val produtoDAO = ProdutoDAO(this@MainActivity)
        val produto = Produto(
            -1 ,  // estrategia pra apenas passa um valor , o banco meesemo naõ pega um valor -1
            titulo , "descricao"
        )
        editTextText.text.clear()
        produtoDAO.salvar(produto)
    }


    private fun ActivityMainBinding.atualizar() {
        val titulo = editTextText.text.toString()

        val produtoDAO = ProdutoDAO(this@MainActivity)
        val produto = Produto(
            -1 ,  // estrategia pra apenas passa um valor , o banco meesemo naõ pega um valor -1
            titulo , "descricao"
        )
        editTextText.text.clear()
        produtoDAO.atualizar(produto)


    }

    private fun ActivityMainBinding.remover() {
        val produtoDAO = ProdutoDAO(this@MainActivity)
        editTextText.text.clear()
        produtoDAO.remover(2)
    }

    private fun ActivityMainBinding.listar() {
        val produtoDAO = ProdutoDAO(this@MainActivity)
        val listaProdutos = produtoDAO.listar()

        if(listaProdutos.isNotEmpty()){
            listaProdutos.forEach {produto ->
                tvResultado.text = "${produto.idProduto} - ${produto.titulo}"
            }
        }

    }
}




