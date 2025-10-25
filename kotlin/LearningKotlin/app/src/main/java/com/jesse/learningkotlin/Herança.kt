package com.jesse.learningkotlin


open class Animal {// classe pai ou superclasse
    var cor:String = "Preto"
    var tamanho:String = ""
    var peso: Double = 0.0


    fun correr() = println("está correndo")
    fun dormir() = println("está dormindo")
}

class Cachorro: Animal() {// classe filha ou subclasse

    fun latir() = println("au au")
}

class passaro: Animal() {
    var especie:String = ""

    fun voar() = println("está voando")
}

fun main() {

}
