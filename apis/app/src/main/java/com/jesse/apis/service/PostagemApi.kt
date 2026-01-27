package com.jesse.apis.service

import com.jesse.apis.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostagemApi {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>

    @GET("posts/{id}")
    suspend fun recuperarPostagem(
        @Path("id")
        id : Int
    ) : Response<Postagem>

}