package com.jesse.learningkotlin


 open class Barco {
     private var modelo:String = ""


    protected open fun injetarCombustivel() {
         println("Injetando combustível no barco")
     }

    fun acelerar() {
        injetarCombustivel()
        println("O barco está acelerando")
    }
}

class mercedes: Barco(){
    override fun injetarCombustivel() {
        println("Injetando combustível no barco Mercedes")
    }
}

fun main() {
    val meuBarco = Barco()
    meuBarco.acelerar()
}