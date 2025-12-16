package com.jesse.navegacao

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.navegacao.databinding.ActivityToolBarBinding

class ToolBarActivity : AppCompatActivity() {
    private val mainn by lazy {
        MainActivity()
    }

    private val binding by lazy{
        ActivityToolBarBinding.inflate(layoutInflater)
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

        inicializarToolBar()
    }

    private fun inicializarToolBar(): Boolean {
        with(binding) {
            materialToolbar.title = "Youtube"
            materialToolbar.setTitleTextColor(
                getColor(R.color.white)
            )
            materialToolbar.subtitle = "Canal do Jesse"
            materialToolbar.setSubtitleTextColor(
                getColor(R.color.white)
            )

            /*
            inflando menus
             */
            materialToolbar.inflateMenu(R.menu.menu_princiapl)

//            setSupportActionBar(binding.materialToolbar)
//            mainn.inicializarActionBar()


            materialToolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.itemPesquisar -> Toast.makeText(
                        this@ToolBarActivity,
                        "Pesquisar",
                        Toast.LENGTH_SHORT
                    ).show()

                    R.id.itemConfiguracoes -> Toast.makeText(
                        this@ToolBarActivity,
                        "Configurações",
                        Toast.LENGTH_SHORT
                    ).show()

                    R.id.itemAdicionar -> Toast.makeText(
                        this@ToolBarActivity,
                        "Adicionar",
                        Toast.LENGTH_SHORT
                    ).show()

                    R.id.itemSair -> finish()
                }
                true
            }
           return true
        }
    }
}