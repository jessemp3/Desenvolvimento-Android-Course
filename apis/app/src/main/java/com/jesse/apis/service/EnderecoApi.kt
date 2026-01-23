package com.jesse.apis.service

import com.jesse.apis.model.Endereco
import retrofit2.Response
import retrofit2.http.GET

interface EnderecoApi {

    @GET("ws/01001000/json/")
    suspend fun recuperarEndereco() : Response<Endereco>
}