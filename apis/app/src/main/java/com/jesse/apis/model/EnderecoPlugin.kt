package com.jesse.apis.model

data class EnderecoPlugin(
    val bairro: String,
    val cep: String,
    val complemento: String,
    val estado: String,
    val localidade: String,
    val logradouro: String,
    val regiao: String,
)