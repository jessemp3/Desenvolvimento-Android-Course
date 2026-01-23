package com.jesse.apis.model

data class Endereco(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val estado: String,
    val regiao: String,
    val ibge: Int,
    val gia: Int,
    val ddd: Int,
    val siafi: Int
)