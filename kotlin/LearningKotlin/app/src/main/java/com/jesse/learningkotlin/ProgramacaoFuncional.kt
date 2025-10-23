package com.jesse.learningkotlin

//função com retorno
fun somar(a: Int, b: Int): Int {
    return a + b
}

//função sem retorno
fun somarSem(a: Int, b: Int) {
    println(a + b)
}


fun main(){
    val resultado = somar(5, 3)
    println("Resultado da soma: $resultado")


    somarSem(10, 7)
}