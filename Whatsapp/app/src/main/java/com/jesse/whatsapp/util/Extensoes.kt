package com.jesse.whatsapp.util

import android.app.Activity
import android.widget.Toast

/// dessa forma qualquer activity hera o exibir mensagem
fun Activity.exibirMensagens(mensagem: String){
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}