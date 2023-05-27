package com.shinjaehun.coroutineexample

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MyAPI {
//    @GET("/comments")
//    fun getComments(): Call<List<Comment>>

    // 아예 받아오는 object를 response로...
    @GET("/comments")
    suspend fun getComments(): Response<List<Comment>>
}