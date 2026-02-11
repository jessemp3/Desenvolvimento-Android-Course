package com.jesse.cep.service

import com.jesse.cep.model.Cep
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {
    @GET("ws/{cep}/json/")
    suspend fun getEndereco(
        @Path("cep")
        cep : String
    ): Response<Cep>
}