package com.jesse.listasecoleoces.testes


fun main() {
    val resultado = calcular(::somar)
    println(resultado)

}

fun somar(n1:Int ,n2:Int):Int{
    return n1 + n2
}

fun calcular(funcaao: (Int, Int) -> Int){

}