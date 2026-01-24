package com.jesse.apis.service

import com.jesse.apis.model.Endereco
import com.jesse.apis.model.EnderecoPlugin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoApi {

    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(
       @Path("cep")
       cep : String
    ) : Response<EnderecoPlugin>
}