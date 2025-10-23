package com.jesse.learningkotlin

//funcao inline
fun subtrair(a:Int ,b: Int = 3):Int = a - b

fun main(){
   val resutl = subtrair(10)
    println("O resultado da subtração é: $resutl")
}