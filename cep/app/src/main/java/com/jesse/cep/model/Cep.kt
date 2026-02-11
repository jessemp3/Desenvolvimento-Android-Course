package com.jesse.cep.model

data class Cep(
    val bairro: String,
    val logradouro: String,
    val cep: String,
    val complemento: String,
    val estado: String,
    val localidade: String,
)