package com.jesse.listasecoleoces.reciclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.jesse.listasecoleoces.R
import com.jesse.listasecoleoces.reciclerView.repository.Mensagem

class MensagemAdapter(
    private val clique: (String) -> Unit // pra poder fazer evento direto na activity
) : Adapter<MensagemAdapter.MensagemViewHolder>() {

    private var listaMensagens =  mutableListOf<Mensagem>()

    fun executarOperacao(){
  // notificar sobre um item alterado
//    listaMensagens[0] = Mensagem(R.drawable.perfil, "kaique testando", "Olá, como vai?", "08:15")
//    notifyItemChanged(0)


//        remove

        listaMensagens.removeAt(4)
        notifyItemRemoved(4)



//        listaMensagens.add(
////            0, // com o index 0 o item vai pro começo da lista e não pro fim
//            Mensagem(R.drawable.perfil, "tuturuuu", "aaaaaa", "20:55")
//        )
//
//        listaMensagens.add(
//            Mensagem(R.drawable.perfil, "testando", "aaaaaa", "20:55")
//
//        )
//
//        adicionar varios intems a partir de um ponto
//        notifyItemRangeChanged(listaMensagens.size,2 )
//        notificar sobre um item , passando o local do novo item como argumento
//        notifyItemInserted(listaMensagens.size)
    }

    fun atualizarListaDados(list : MutableList<Mensagem>){
//        listaMensagens.addAll(list)
        listaMensagens = list

//        notifyDataSetChanged()
    // a questão do notify é que ele atualiza todo o conjunto de dados e não apenas o dados que foi att
    }


    inner class MensagemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imagePerfil: ImageView = view.findViewById(R.id.imageCardPerfil)
        val textNome: TextView = view.findViewById(R.id.textViewCardName)
        val textMensagem: TextView = view.findViewById(R.id.textViewCardMessage)
//        val textHora: TextView = view.findViewById(R.id.textHora)



        fun bind(mensagem: Mensagem){
            //pegando o contexto do item
//            val contextImage = imagePerfil.context
            imagePerfil.setOnClickListener {
                clique(mensagem.nome)
            }


        }
    }


    // ao criar o viewHolder -> cria a vizualização
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MensagemViewHolder {
        // inflando um layout manualmente pra poder passar como view que o viewHolder esperar receber
        val layoutinflater = LayoutInflater.from(parent.context)

        //e aqui estou literalmente inflando a view do layout
        val itemView = layoutinflater.inflate(
            R.layout.item_cardview,
            parent,
            false
        )

        return MensagemViewHolder(itemView)
    }


    // ao vincular os dados ao viewHolder
    override fun onBindViewHolder(
        holder: MensagemViewHolder,
        position: Int
    ) {
        val mensagem = listaMensagens[position]
        holder.textNome.text = mensagem.nome
        holder.textMensagem.text = mensagem.ultima
//        holder.textHora.text = mensagem.hora
        holder.imagePerfil.setImageResource(mensagem.imagem)

        // aqui que os eventos de clique acontececem
        holder.bind(mensagem)


    }

    // retorna a quantidade de itens da lista
    override fun getItemCount(): Int {
        return listaMensagens.size
    }


}