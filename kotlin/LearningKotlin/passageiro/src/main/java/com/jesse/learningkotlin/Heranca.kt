package com.jesse.learningkotlin


abstract class Animal {// classe pai ou superclasse , abstract impede a instanciação direta da classe
    var cor:String = "Preto"
    var tamanho:String = ""
    var peso: Double = 0.0


   open fun correr() = println("está correndo")
   abstract  fun dormir()  // metodo abstrato, obrigatorio a implementação nas classes filhas
}

class Cachorro: Animal() {// classe filha ou subclasse

    fun latir() = println("au au")
    override fun correr() {
        print("o cachorro ")
        super.correr()
    }

    override fun dormir() {
        println("o cachorro está dormindo")
    }
}

class passaro: Animal() {
    var especie:String = ""

    fun voar() = println("está voando")
    override fun correr() {
        print("o pássaro ")
        super.correr()
    }

    override fun dormir() {
        println("o pássaro está dormindo")
    }
}

fun main() {
    val dog = Cachorro()
    dog.correr()

    val papagaio = passaro()
    papagaio.correr()
}
