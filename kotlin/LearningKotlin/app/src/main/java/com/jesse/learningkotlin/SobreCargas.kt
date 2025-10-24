package com.jesse.learningkotlin

class Usuario{

    // assinatura de metodo = nome + parametros + tipo de retorno
    fun logar(email:String , senha:String){
        println("Logado com email: $email")
    }

    //sobrecarga de metodo
    fun logar(numeroTelefone:String){
        println("Logado com telefone: $numeroTelefone")
    }
}


fun main(){
    val user = Usuario()
    user.logar("sick@gmail.com" , "123456")
    user.logar("11999999999")
}