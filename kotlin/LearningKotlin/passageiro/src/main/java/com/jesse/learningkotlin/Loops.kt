package com.jesse.learningkotlin

fun main(){

    //array

    // como já sabemos , val é uma constante , mas no caso de arrays , podemos alterar pq os valore
    // alterados estão dentro do array e não na variável que referencia o array (nome)
    val nomes: Array<String> = arrayOf("Jesse", "Maria", "João", "Ana")

    println(nomes[2])

// forma mt boa de percorrer uma array ou lista
    //.withIndex() -> traz o índice e o valor
    for ((i, nome )in nomes.withIndex()){
        println("I: $i Nome: $nome")
    }


    //loops

    //while

//    var numero = 1
//    while(numero <= 10){
//        println("Número $numero")
//        numero++
//    }


    //for

    for (i in 1..6){
        println("Número $i")
    }

}