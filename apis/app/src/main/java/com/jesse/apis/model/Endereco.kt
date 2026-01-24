package com.jesse.apis.model

import com.google.gson.annotations.SerializedName

data class Endereco(

    @SerializedName("cep") // com o serialized , eu consigo passar a propriedade com um nome diferente , pq o serialized já tem o nome da propriedade
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
)