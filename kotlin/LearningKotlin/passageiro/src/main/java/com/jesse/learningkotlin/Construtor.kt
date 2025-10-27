package com.jesse.learningkotlin


class User constructor(nome:String , sobrenome:String) { //construtor primario
    var nome: String = "" // é possivel ja passar esses valores direto no construtor primario
    var sobrenome:String = ""


    init { // bloco de inicializacao (não necessariamente tem a ver com o construtor)
        this.nome = nome
        this.sobrenome = sobrenome
        println("Usuario criado: $nome $sobrenome")
    }

    constructor(nome:String):this(nome, ""){ // construtor secundario
        println("construtor secundario chamado")
    }
}

fun main(){
    // sobrecarga de construtor
    val user1 = User("kaique")
}