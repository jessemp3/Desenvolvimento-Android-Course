package com.jesse.listasecoleoces.testes

class Motorista(val nome:String){

    fun exibirDados() = print("motorisita: $nome")

    // nested class
    class Caminhao(val nomeCaminhao:String){
        fun exibirDadosCaminhao() = print("motorisita: $nomeCaminhao")
    }

    // inner class
    inner class Carro(val nomeCarro:String){
        fun exibirDadosCarro() = print("motorisita: $nomeCarro motorista: $nome")
    }
}

fun main() {
    val motorista = Motorista("jesse")
    val caminhao = Motorista.Caminhao("Volvo ")
    val carro = motorista.Carro("Mercedes")
}