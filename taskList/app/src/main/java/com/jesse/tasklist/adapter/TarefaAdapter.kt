package com.jesse.tasklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesse.tasklist.databinding.ItemTarefaBinding
import com.jesse.tasklist.model.Tarefa

class TarefaAdapter() : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listaTarefas: List<Tarefa> = listOf()

    fun adicionarLista(lista :List<Tarefa>){
        this.listaTarefas = lista
        notifyDataSetChanged()
    }

    inner class TarefaViewHolder(itemBinding: ItemTarefaBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTarefaBinding = itemBinding

        fun bind(tarefa: Tarefa){
            binding.textViewDescricao.text = tarefa.descricao
            binding.textViewData.text = tarefa.dataCadastro
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val itemTarefaBinding = ItemTarefaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TarefaViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]
        holder.bind(tarefa)
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }

}