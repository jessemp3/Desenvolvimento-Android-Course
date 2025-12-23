package com.jesse.tasklist.database

import com.jesse.tasklist.model.Tarefa

interface ITarefaDAO {
    fun salvar(tarefa: Tarefa): Boolean
    fun atualizar(tarefa: Tarefa): Boolean
    fun remover(idTarefa: Int): Boolean
    fun listar(): List<Tarefa>
}