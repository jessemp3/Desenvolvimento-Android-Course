package com.jesse.classfirebase.helper

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissao {
    companion object {
        fun requisitarPermisoes(activity: Activity, permisoes: List<String>) {


            //checar se tem permissao
            ContextCompat.checkSelfPermission(
                activity,
                permisoes.first()
            )

            val permisoesNegadas = mutableListOf<String>()
            permisoes.forEach { permisao ->
                val temPermissao = ContextCompat.checkSelfPermission(
                    activity, permisao
                ) == PackageManager.PERMISSION_GRANTED


                if (!temPermissao) {
                    permisoesNegadas.add(permisao)
                }
            }

            if (permisoesNegadas.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    activity,
                    permisoesNegadas.toTypedArray(),
                    0
                )
            }

        }
    }
}