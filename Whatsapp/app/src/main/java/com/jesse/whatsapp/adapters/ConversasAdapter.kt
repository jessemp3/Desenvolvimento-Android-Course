package com.jesse.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.jesse.whatsapp.databinding.ItemConversasBinding
import com.jesse.whatsapp.model.Conversa

class ConversasAdapter(
    private val onClick: (Conversa) -> Unit
): Adapter<ConversasAdapter.ConversasViewHolder>() {

    private var listaConversas = emptyList<Conversa>()
    fun adicionarLista(lista: List<Conversa>) {
        listaConversas = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): ConversasViewHolder {
        val itemView = ItemConversasBinding.inflate(
            LayoutInflater.from(p0.context),
            p0,
            false
        )
        return ConversasViewHolder(itemView)
    }

    override fun onBindViewHolder(
        p0: ConversasViewHolder,
        p1: Int
    ) {
        val conversa = listaConversas[p1]
        p0.bind(conversa)
    }

    override fun getItemCount(): Int {
        return listaConversas.size
    }


    inner class ConversasViewHolder(
        private val binding: ItemConversasBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(conversa: Conversa) {
            binding.textViewNomeConversa.text = conversa.nome
            binding.textViewUltimaMensagem.text = conversa.ultimaMensagem
            Glide.with(binding.root)
                .load(conversa.foto)
                .into(binding.imageViewPerfilConversa)

            binding.itemConversa.setOnClickListener {
                onClick(conversa)
            }
        }
    }
}