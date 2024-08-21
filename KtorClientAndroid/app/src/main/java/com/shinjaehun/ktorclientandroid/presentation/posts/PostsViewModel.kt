package com.shinjaehun.ktorclientandroid.presentation.posts

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinjaehun.ktorclientandroid.data.remote.PostsServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PostsViewModel"

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val httpClient: HttpClient

): ViewModel() {

    private val _state = mutableStateOf(PostsState())
    val state: State<PostsState> = _state

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            try {
                val client = PostsServiceImpl(client=httpClient)

                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    postResponse = client.getPosts(),
                    isLoading = false
                )
//                Log.i(TAG, "response: ${_state.value.postResponse}")
            } catch (e: Exception) {
                Log.e(TAG, "error: $e")
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }
}