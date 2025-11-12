package com.jesse.listasecoleoces.testes

class User (){
    var nome:String = ""
        get() {
            print(nome)
            return field
        }
        set(value) {
            field = value
        }
    var age:Int = 0
        get() = field
        set(value) {
            field = value
        }
}

fun main() {
    val pesssoa = Acessores()
    pesssoa.nome = "kaique"
    pesssoa.age = 20
}