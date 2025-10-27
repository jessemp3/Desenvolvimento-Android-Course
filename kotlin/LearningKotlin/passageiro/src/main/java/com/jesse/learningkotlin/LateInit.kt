package com.jesse.learningkotlin

class Navio {
    lateinit var descricao: String
    lateinit var capitão: String

    fun mostrarDetalhes() {
        println("Descrição: $descricao")
        println("Capitão: $capitão")
    }
}

fun main() {
    var navio = Navio()
    navio.descricao = "Navio cargueiro"
    navio.capitão = "Capitão Jack Sparrow"
    navio.mostrarDetalhes()
}