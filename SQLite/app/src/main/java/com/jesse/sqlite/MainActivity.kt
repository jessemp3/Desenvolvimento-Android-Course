package com.jesse.sqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jesse.sqlite.database.DataBaseHelper

class MainActivity : AppCompatActivity() {
    private val bancoDeDados by lazy{
        DataBaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {
            bancoDeDados.writableDatabase.execSQL(
                "insert into produtos values (null , 'MackBook' , 'Mackbook é bom pra trabalhar com kmp');"
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}