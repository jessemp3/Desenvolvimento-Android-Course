package com.jesse.binding

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.jesse.binding.databinding.ActivityFabactivityBinding
import com.jesse.binding.databinding.ActivityMainBinding

class FABActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFabactivityBinding.inflate(layoutInflater)
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
            fab.setOnClickListener {
                if(groupMenu.isVisible){
                    groupMenu.visibility = View.GONE
                }else{
                    groupMenu.visibility = View.VISIBLE
                }
            }
        }
    }
}