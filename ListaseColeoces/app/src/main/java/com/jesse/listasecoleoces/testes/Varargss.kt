package com.jesse.listasecoleoces.testes

class Varargss {
    fun tefs(vararg telefone:String){
        for(t in telefone){
            println(t)
        }
    }
}

fun main() {
    val pessoa = Varargs()
    pessoa.salvarTefs("11 1234-2345", "12 2343-2334" , "23 1234-2334")

    val pessoa2 = Varargss()
    pessoa2.tefs("11 1234-2345", "12 2343-2334" , "23 1234-2334")
}