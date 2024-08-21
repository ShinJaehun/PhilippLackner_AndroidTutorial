package com.shinjaehun.ktorclientandroid.data.remote

import com.shinjaehun.ktorclientandroid.data.remote.dto.PostRequest
import com.shinjaehun.ktorclientandroid.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.serializer
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface PostsService {

    suspend fun getPosts(): List<PostResponse>
//    suspend fun getPosts(): HttpResponse 이걸 viewmodel에서 받아서 처리하는 건 나쁜거지?

    suspend fun createPost(postRequest: PostRequest): PostResponse?

    // di 적용하면 얘는 여기 있으면 안됩니다
//    companion object {
//        fun create(): PostsService {
//            return PostsServiceImpl(
//                client = HttpClient(Android) {
//                    install(Logging) {
//                        level = LogLevel.ALL
//                    }
//                    install(ContentNegotiation) {
//                        json(Json{
//                            prettyPrint = true
//                            isLenient = true
//                            ignoreUnknownKeys = true
//                        })
//                    }
//                }
//            )
//        }
//    }
}