package com.shinjaehun.ktorclientandroid.presentation.auth

import android.content.Context
import android.telecom.Call
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinjaehun.ktorclientandroid.data.remote.AuthServiceImpl
import com.shinjaehun.ktorclientandroid.data.remote.dto.PostRequest
import com.shinjaehun.ktorclientandroid.data.remote.dto.SignInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

private const val TAG = "AuthViewModel"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val httpClient: HttpClient
) : ViewModel() {

    fun performSignIn(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
               val client = AuthServiceImpl(httpClient)
               client.signIn(SignInRequest(email=email, password=password))
            } catch (e: Exception) {
                Log.e(TAG, "Error: $e.message")
            }
        }

        // 그니까 context는 이렇게 okhttp로 통신할 때 필요했던 걸까?
//        val client = OkHttpClient()
//
//        val requestBody = FormBody.Builder()
//            .add("email", email)
//            .add("password", password)
//            .build()
//
//        val request = Request.Builder()
//            .url(API_SIGN_IN_URL)
//            .post(requestBody)
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                // Handle network failure or API error
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                if (response.isSuccessful) {
//                    val authToken = response.header("X-Session-Token")
//                    val cookies = response.headers("Set-Cookie")
//
//                    saveAuthToken(context, authToken)
//                    saveCookies(cookies)
//
//                    // execute on the main thread
//                    requireActivity().runOnUiThread {
//                        navigateUp()
//                        sessionNavHostFragment.reset()
//                    }
//
//                } else {
//                    // Raise error
//                }
//            }
//        })
    }
}