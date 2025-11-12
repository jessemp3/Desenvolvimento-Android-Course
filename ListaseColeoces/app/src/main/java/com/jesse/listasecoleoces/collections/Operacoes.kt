package com.jesse.listasecoleoces.collections

fun main() {

}

fun filter(){
    val listaFrutas = listOf("maça" , "laranja", "banana")
    listaFrutas.filter { fruta ->
        fruta == "laranja"
    }
}