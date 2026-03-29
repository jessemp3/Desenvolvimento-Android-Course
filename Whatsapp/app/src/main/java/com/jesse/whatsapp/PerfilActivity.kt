package com.jesse.whatsapp

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.whatsapp.databinding.ActivityPerfilBinding
import com.jesse.whatsapp.util.setup

class PerfilActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPerfilBinding.inflate(layoutInflater)
    }

    private var temPermisaoGaleria = false
    private var temPermisaoCamera = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        solicitarPermisoes()

        binding.includePerfilToolbar.setup(
            title = "Perfil",
            onBack = {
                finish()
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun solicitarPermisoes() {
        //verificar se já tem a permisao
        temPermisaoCamera = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        temPermisaoGaleria = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED


        // lista de negadas
        val listaPermisoesNegadas = mutableListOf<String>()
        if(!temPermisaoCamera ){
            listaPermisoesNegadas.add(android.Manifest.permission.CAMERA)
        }
        if(!temPermisaoGaleria){
            listaPermisoesNegadas.add(android.Manifest.permission.READ_MEDIA_IMAGES)
        }

        if(listaPermisoesNegadas.isNotEmpty()){
            //solicitar multiplicar permisoes
            val gerenciadorPermisoes = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ){permisoes ->
                temPermisaoCamera = permisoes[android.Manifest.permission.CAMERA] ?: temPermisaoCamera
                temPermisaoGaleria = permisoes[android.Manifest.permission.READ_MEDIA_IMAGES] ?: temPermisaoGaleria
            }

            gerenciadorPermisoes.launch(listaPermisoesNegadas.toTypedArray())
        }



    }
}