package com.jesse.apis.service

import com.jesse.apis.model.Postagem
import retrofit2.Response
import retrofit2.http.GET

interface PostagemApi {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>

}