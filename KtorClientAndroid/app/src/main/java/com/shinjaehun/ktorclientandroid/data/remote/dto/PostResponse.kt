package com.shinjaehun.ktorclientandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val body: String,
    val title: String,
    val id: Int,
    val user_id: Int
)
