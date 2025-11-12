package com.jesse.listasecoleoces.testes

// a date class é uma classe focada em cuidados dos dados
data class Perguntas(val perguntas:String, val respostasCerta:Int){

}


fun main() {
    val pergunta1 = Perguntas("sim" , 0)
    val pergunta2 = Perguntas("sim" , 0)

    //destructing
   val (pergunta, respota) = pergunta1 // literalmente estou atribundo o valor de pergunta a varial pergunta entre parenteses e resposta a resposta skk
    //   sim,    0

    /*
    diferente de uma classe normal , essa verifição vai ser true
    pq a classe date foca nos dados por tanto ela não vai gera um id difernte pra cada instancia criada
     */
    if(pergunta2 == pergunta1){
        println("iguai")
    }


}