package com.jesse.learningkotlin

fun main(){
    //motorista
    val idade:Int = 17

    if (idade >= 18){
        println("Pode tirar carteira de motorista")
    } else {
        println("Não pode tirar carteira de motorista")
    }

    //banco
    val opcao:Int = 3

    if(opcao in 1..3){
        println("cartão de crédito")
    }

    //estado
    val estado:String = "SP"

    when(estado){
        "SP" -> println("São Paulo")
        "RJ" -> println("Rio de Janeiro")
        "MG" -> println("Minas Gerais")
        else -> println("Outro estado")
    }
}