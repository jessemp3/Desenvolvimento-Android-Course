package com.jesse.whatsapp.activitys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.jesse.whatsapp.R
import com.jesse.whatsapp.adapters.ViewPagerAdapter
import com.jesse.whatsapp.databinding.ActivityMainBinding
import com.jesse.whatsapp.util.setup

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initNavigation()

        with(binding) {
            includeMainToolbar.setup(
                title = "Whatsapp",
                onBack = {
                    finish()
                }
            )

            includeMainToolbar.toolbar.navigationIcon = null
            includeMainToolbar.toolbar.addMenuProvider(
                object : MenuProvider {
                    override fun onCreateMenu(p0: Menu, p1: MenuInflater) {
                        menuInflater.inflate(R.menu.menu_principal, p0)
                    }

                    override fun onMenuItemSelected(p0: MenuItem): Boolean {
                        when (p0.itemId) {
                            R.id.menu_sair -> {
                                deslogarUsuario()
                            }

                            R.id.menu_perfil -> {
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        PerfilActivity::class.java
                                    )
                                )
                            }
                        }
                        return true
                    }

                }
            )
        }

    }

    private fun initNavigation() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPagerPrincipal

        //adapert

        val abas =  listOf("Conversas" , "Contatos")
        viewPager.adapter = ViewPagerAdapter(
            abas,
            supportFragmentManager,
            lifecycle
        )

        tabLayout.isTabIndicatorFullWidth = true
        TabLayoutMediator(
            tabLayout,
            viewPager
        ){aba , posicao ->
            aba.text = abas[posicao]
        }.attach()
    }


    private fun deslogarUsuario() {
        AlertDialog.Builder(this)
            .setTitle("Deslogar")
            .setMessage("Deseja realmente sair ?")
            .setPositiveButton("Sim") { _, _ ->
                firebaseAuth.signOut()
                finish()
            }
            .setNegativeButton("Não") { _, _ ->

            }
            .create()
            .show()
    }
}