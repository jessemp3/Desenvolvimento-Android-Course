package com.jesse.listasecoleoces.testes

fun main() {
    val lista = arrayOf("eu" , 10)
    lista.plus("a")//plus cria uma nova instacia da lista
    lista.size //tamanho da lista
    lista.indexOf(10) // retorna o index do valor
    lista.first()//retorna o primero valor
    lista.last()// retorna o utlimo valor
    lista.contains("eu") // verifica se existe o valor (true e false)
    lista.shuffle()// embaralha os valores

    lista.forEach {
        it -> println(it)
    }
}