package com.shinjaehun.ktorclientandroid.presentation.posts

import com.shinjaehun.ktorclientandroid.data.remote.dto.PostResponse

data class PostsState(
    val postResponse: List<PostResponse>? = null,
    val isLoading: Boolean = false
)