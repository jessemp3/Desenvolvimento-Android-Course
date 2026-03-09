package com.jesse.classfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.jesse.classfirebase.databinding.ActivityUploadImageBinding

class UploadImageActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUploadImageBinding.inflate(layoutInflater)
    }

    private val abrirGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){uri -> 
        if(uri != null) {

            Glide.with(this)
                .load(uri)
                .centerCrop()
                .into(binding.imageView)

            Toast.makeText(this, "Imagem selecionada", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Nenhuma imagem selecioanda", Toast.LENGTH_SHORT).show()
        }
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
            imageBtnInsert.setOnClickListener {
                abrirGaleria.launch("image/*")//mime type
            }
        }
    }
}