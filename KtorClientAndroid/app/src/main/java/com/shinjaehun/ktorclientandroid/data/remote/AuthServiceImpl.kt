package com.shinjaehun.ktorclientandroid.data.remote

import android.util.Log
import com.shinjaehun.ktorclientandroid.data.remote.dto.SignInRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

private const val TAG = "AuthServiceImpl"

class AuthServiceImpl(
    private val client: HttpClient,
): AuthService {

    override suspend fun signIn(signInRequest: SignInRequest) {
        try {
            val httpResponse = client.post(HttpRoutes.SIGN_IN) {
                contentType(ContentType.Application.Json)
                setBody(signInRequest)
            }
            if (httpResponse.status.value in 200..299) {
                Log.i(TAG, "Successful response!")
            } else {
                Log.e(TAG, "http error: ${httpResponse.status.value}")
            }
//            Log.i(TAG, "response: $httpResponse")
//            handleAuthResponse(httpResponse)
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
        }
    }

//    private suspend fun handleAuthResponse(response: HttpResponse) {
//        if (response.status.value in 200..299) {
//            Log.i(TAG, "Successful response!")
//        }
        // 이렇게 response를 받아서 뭔가 처리하고 싶었는데
        // response.body()

        // 빌어먹을 자바스크립트로
        // const data = await response.json(); 이렇게 처리하면 깔끔하게 끝나는 것을...
        // 심지어 이렇게 간단하게 다룰 수도 있는데... 결국 포기

        // access_token = data.token;
        // refresh_token = data.refresh_token;
        // resource_owner = data.resource_owner;

//        val data = response.body<SignInResponse>()

//        Log.i(TAG, "response body: $data")
//        Log.i(TAG, "response token: ${data.token}")
//        Log.i(TAG, "response refresh_token: ${data.refreshToken}")
//        Log.i(TAG, "response resource_owner: ${data.resourceOwner}")
//        val resource_owner = data.resourceOwner as User
//        Log.i(TAG, "response resourceOwner: ${resource_owner}")

//    }

}