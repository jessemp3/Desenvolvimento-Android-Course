package com.jesse.listasecoleoces.collections

fun main() {

}

fun filter(){
    val listaFrutas = listOf("maça" , "laranja", "banana")
    listaFrutas.filter { fruta ->
        fruta == "laranja"
    }
}

/*
cria uma lista com o mesmo número de itens ou os itens transformados
 */
fun map(){
    val lista = listOf("jesse", "kaique" , "cice")

    val novalista = lista.map { nome -> // it.uppercase()
        nome.uppercase()
    }
}


// faz a junção de itens de duas listas
fun union(){
    val listaLanches = listOf("mclancheFeliz" , "refri")
    val filme = listOf("pipoca" , "refri")

    val novalista = listaLanches.union(filme)
}


// asc -> ascedente
// desc -> descendente
fun ordenacao(){

}