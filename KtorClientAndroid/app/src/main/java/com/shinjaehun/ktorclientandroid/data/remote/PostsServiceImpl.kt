package com.shinjaehun.ktorclientandroid.data.remote

import com.shinjaehun.ktorclientandroid.data.remote.dto.PostRequest
import com.shinjaehun.ktorclientandroid.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {

    override suspend fun getPosts(): List<PostResponse> {
        return try {
            val httpResponse = client.get {
                url(HttpRoutes.POSTS)
            }
            httpResponse.body()
        } catch(e: RedirectResponseException) {
            // 3xx -response
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ClientRequestException) {
            // 4xx -response
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ServerResponseException) {
            // 5xx -response
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }

    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            val httpResponse = client.post(HttpRoutes.POSTS) {
                contentType(ContentType.Application.Json)
                setBody(postRequest)
            }
            httpResponse.body()
        } catch(e: RedirectResponseException) {
            // 3xx -response
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx -response
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx -response
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }

    }
}