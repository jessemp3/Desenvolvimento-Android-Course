package com.jesse.activityfragment

import java.io.Serializable

data class Filme(
    val nome:String,
    val description:String,
    val avaliacoes:Double,
    val diretor:String,
    val distribuidora:String,
    val anoLancamento:Int
) : Serializable
