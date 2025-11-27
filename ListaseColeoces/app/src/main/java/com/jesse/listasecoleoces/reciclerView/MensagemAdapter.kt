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
    private val lista: List<Mensagem>
) : Adapter<MensagemAdapter.MensagemViewHolder>() {

    inner class MensagemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imagePerfil: ImageView = view.findViewById(R.id.imageCardPerfil)
        val textNome: TextView = view.findViewById(R.id.textViewCardName)
        val textMensagem: TextView = view.findViewById(R.id.textViewCardMessage)
//        val textHora: TextView = view.findViewById(R.id.textHora)
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
        val mensagem = lista[position]
        holder.textNome.text = mensagem.nome
        holder.textMensagem.text = mensagem.ultima
//        holder.textHora.text = mensagem.hora
        holder.imagePerfil.setImageResource(mensagem.imagem)

    }

    // retorna a quantidade de itens da lista
    override fun getItemCount(): Int {
        return lista.size
    }


}