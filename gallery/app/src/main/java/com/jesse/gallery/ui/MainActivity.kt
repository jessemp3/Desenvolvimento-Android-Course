package com.jesse.gallery.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jesse.gallery.R
import com.jesse.gallery.adapter.FotosAdapter
import com.jesse.gallery.databinding.ActivityMainBinding
import com.jesse.gallery.date.ListOfFotos.Companion.fotosData

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.reclycerView.adapter = FotosAdapter(fotosData)
        binding.reclycerView.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
//        binding.reclycerView.layoutManager = GridLayoutManager(this, 2)

    }
}