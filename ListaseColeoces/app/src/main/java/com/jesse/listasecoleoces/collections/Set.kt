package com.jesse.listasecoleoces.collections

data class Carro(
    val nome:String,
    val marca:String
)

fun main() {
    val lista = setOf("kaique" , "eu", "eu") // mesmo que tenha item repetido , na hora que ele vai ser passado pelo foreach ele retira o item repetido
    //set não tem acesso ao lista[0]
    lista.forEach { it -> println(it) }


    val lista1 = mutableSetOf(
        Carro("kaique" , "Mercedez"),
        Carro("kaique" , "BMW"),
    )
}