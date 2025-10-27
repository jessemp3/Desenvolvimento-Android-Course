package com.jesse.learningkotlin

class Car
  (  var modelo:String = "mercedes",
    var velocidae:Int = 10){

    companion object{// uma forma de ter usar valores sem precisar instanciar a classe
        const val  VELOCIDADE_MAX :Int = 300
        fun exibirVelocidadeMaxima(){
            println("A velocidade maxima é de $VELOCIDADE_MAX km/h")
        }
    }


    fun exibirInformacoes(){
        println("Modelo: $modelo")
        println("Velocidade: $velocidae km/h")
    }
}

fun main() {
    val carro1 = Car("Fusca", 120)
    carro1.exibirInformacoes()

   println(Car.exibirVelocidadeMaxima())// asssim , isso não é um atributo de instacia e sim da classe
}