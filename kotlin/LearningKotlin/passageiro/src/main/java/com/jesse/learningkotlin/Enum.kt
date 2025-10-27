package com.jesse.learningkotlin

enum class PedidoStatus(val descricao: String) {
    AGUARDANDO_PAGAMENTO("Aguardando pagamento"), // 0
    EM_SEPARACAO("Em separação"), // 1
    ENVIADO("Enviado"),// 2
    ENTREGUE("Entregue")// 3
}


class Pedido(
    var total: Double = 0.0,
    var itens: String = "",
    var statusPedido: PedidoStatus = PedidoStatus.AGUARDANDO_PAGAMENTO
) {

}

fun main() {
    // tela de compra
    val pedido = Pedido(100.0 , "Camisa, Caneca", PedidoStatus.AGUARDANDO_PAGAMENTO)
    println("Total do pedido: ${pedido.total} itens: ${pedido.itens}")


    pedido.statusPedido = PedidoStatus.EM_SEPARACAO

     println("Atualizando o status do pedido para: ${pedido.statusPedido.descricao}")
    // ordinal guarda a posição do enum na lista

    if(pedido.statusPedido == PedidoStatus.AGUARDANDO_PAGAMENTO){
        println("O pedido está com o status: ${pedido.statusPedido.descricao}")
    }else if(pedido.statusPedido == PedidoStatus.EM_SEPARACAO){
        println("O pedido está com o status: ${pedido.statusPedido.descricao}")
    }else if(pedido.statusPedido == PedidoStatus.ENVIADO){
        println("O pedido está com o status: ${pedido.statusPedido.descricao}")
    }else if(pedido.statusPedido == PedidoStatus.ENTREGUE) {
        println("O pedido está com o status: ${pedido.statusPedido.descricao}")
    }
}