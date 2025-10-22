package com.jesse.learningkotlin


class Carro {
    val cor = "Vermelho"

    fun acelerar(){
        println("Acelerando o carro")
    }
}


fun main (){
    val carro: Carro?
    carro = Carro()
    carro?.acelerar()
    val cor = carro?.cor ?: "preto"

    println("A cor do carro é $cor")
}