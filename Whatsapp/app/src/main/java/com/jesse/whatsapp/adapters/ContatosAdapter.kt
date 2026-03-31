package com.jesse.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jesse.whatsapp.databinding.ItemContatoBinding
import com.jesse.whatsapp.model.Usuario

class ContatosAdapter : RecyclerView.Adapter<ContatosAdapter.ContatosViewHolder>() {

    private var listaContatos = emptyList<Usuario>()
    fun adicionarLista(lista: List<Usuario>) {
        listaContatos = lista
        notifyDataSetChanged()
    }


    inner class ContatosViewHolder(
        private val binding: ItemContatoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(usuario: Usuario) {
            binding.textViewContatoNome.text = usuario.nome
            Glide.with(binding.root)
                .load(usuario.foto)
                .into(binding.imageViewContatoPerfil)
        }
    }

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): ContatosViewHolder {
        val itemView = ItemContatoBinding.inflate(
            LayoutInflater.from(p0.context),
            p0,
            false
        )
        return ContatosViewHolder(itemView)
    }

    override fun onBindViewHolder(
        p0: ContatosViewHolder,
        p1: Int
    ) {
        val usuario = listaContatos[p1]
        p0.bind(usuario)
    }

    override fun getItemCount(): Int {
        return listaContatos.size
    }


}