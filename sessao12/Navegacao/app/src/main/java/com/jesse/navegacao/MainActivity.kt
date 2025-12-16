package com.jesse.navegacao

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    // forma de esconder a actionbar
    //        supportActionBar?.hide()

        inicializarActionBar()
    }

    /*
    usando menu provider, outra forma de criar os menus
     */
    private fun inicializarActionBar() {
        addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(
                    menu: Menu,
                    menuInflater: MenuInflater
                ) {
                    menuInflater.inflate(R.menu.menu_princiapl, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.itemPesquisar -> Toast.makeText(
                            this@MainActivity,
                            "Pesquisar",
                            Toast.LENGTH_SHORT
                        ).show()

                        R.id.itemConfiguracoes -> Toast.makeText(
                            this@MainActivity,
                            "Configurações",
                            Toast.LENGTH_SHORT
                        ).show()

                        R.id.itemAdicionar -> Toast.makeText(
                            this@MainActivity,
                            "Adicionar",
                            Toast.LENGTH_SHORT
                        ).show()

                        R.id.itemSair -> finish()
                    }
                    return true
                }

            }

        )
    }


    // quando a tela é carregada , esse metodo é chamado e ele infla um layout pra contruir o menu (se ouver)
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        /*
//       aqui , o menu foi inflado , porem o itens ainda não são cliveis(só os 3 pontinhos né , obvio)
//         */
//        menuInflater.inflate(R.menu.menu_princiapl , menu)
//
//        return true
//    }

    /*
    metodo pra quando eu clicar nos itesn
     */
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.itemPesquisar -> Toast.makeText(this , "Pesquisar" , Toast.LENGTH_SHORT).show()
//            R.id.itemConfiguracoes -> Toast.makeText(this , "Configurações" , Toast.LENGTH_SHORT).show()
//            R.id.itemAdicionar -> Toast.makeText(this , "Adicionar" , Toast.LENGTH_SHORT).show()
//            R.id.itemSair ->  finish()
//        }
//        return true
//    }


}