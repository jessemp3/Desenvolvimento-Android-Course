package com.jesse.activityfragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

//data class Filme(
//    val nome:String,
//    val description:String,
//    val avaliacoes:Double,
//    val diretor:String,
//    val distribuidora:String,
//    val anoLancamento:Int
//) : Serializable

@Parcelize
data class Filme(
    val nome:String,
    val description:String,
    val avaliacoes:Double,
    val diretor:String,
    val distribuidora:String,
    val anoLancamento:Int
) : Parcelable