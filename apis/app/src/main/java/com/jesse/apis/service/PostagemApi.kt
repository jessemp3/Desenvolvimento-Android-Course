package com.jesse.apis.service

import com.jesse.apis.model.Comentario
import com.jesse.apis.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostagemApi {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>

    @GET("posts/{id}/comments")
    suspend fun recuperarComentarioParaPostagem(
        @Path("id")
        id : Int
    ) : Response<List<Comentario>>


    @GET("comments") // coments?postId=1
    suspend fun recuperarComentarioParaPostagemQuery(
        @Query("postId")
        postId : Int
    ) : Response<List<Comentario>>

    @POST("posts")
    suspend fun salvarPostagem(
        @Body postagem: Postagem
    ): Response<Postagem>

    @FormUrlEncoded
    @POST("posts")
    suspend fun salvarPostagemFormulario(
        @Field("userId")
        userId: Int,
        @Field("id")
        id: Int,
        @Field("title")
        title: String,
        @Field("body")
        body: String
    ): Response<Postagem>

}