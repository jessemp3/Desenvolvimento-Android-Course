package com.jesse.listasecoleoces.testes

data class Produto(
    var nome: String,
    var preco: Double
){
    fun desativar(){
        println("produto desativado")
    }
}

fun salvarProduto(produto: Produto){

}

fun main() {
    var produto: Produto? = null
    // user é que escolhe ou não o produto

    produto = Produto("mouse", 100.00)

    if(produto != null){
        produto.preco = 1100.00
        salvarProduto(produto)
    } // esse bloco de validação pode ser feito usando let

  val novoProduto = produto?.let {item ->
        item.preco = 1000.00
        item.nome = "celular"
        salvarProduto(produto)
      item // assim seria como dar um return e estou retornado um outro produto
    }


    produto?.run {
//        this.nome = "teclado"
//        this.preco = 100.00"
        desativar()
    }// util pra inicializar o objeto é como se eu estivesse dentro do proprio objeto


    with(produto){
        this.nome = "mouse"
    }// o with é mt semelhante ao run , muda mais a sintaxe mesmo
}