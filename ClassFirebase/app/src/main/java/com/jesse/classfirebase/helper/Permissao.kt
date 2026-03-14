package com.jesse.classfirebase.helper

import android.app.Activity
import androidx.core.app.ActivityCompat

class Permissao {
    companion object{
        fun requisitarPermisoes(activity: Activity , permisoes: List<String>){
            ActivityCompat.requestPermissions(
                 activity,
                permisoes.toTypedArray(),
                0
            )
        }
    }
}