package com.jesse.learningkotlin


interface Presidenciavel{
    fun candidatar()
}

abstract class Pessoa {
    fun comer() = println("A pessoa está comendo")
}

class devAndroid: Pessoa(), Presidenciavel {
    fun programar() = println("O dev Android está programando")

    override fun candidatar() {
        println("O dev Android está se candidatando")
    }
}

class devWeb: Pessoa() , Presidenciavel {
    fun programar() = println("O dev Web está programando")
    override fun candidatar() {
        TODO("Not yet implemented")
    }
}


fun main() {
    val pessoa1: Presidenciavel = devAndroid()
    pessoa1.candidatar()


    println("-----")

    val pessoa2: Pessoa = devWeb()
    pessoa2.comer()

}