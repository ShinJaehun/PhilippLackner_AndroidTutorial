package com.shinjaehun.ktorclientandroid.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val body: String,
    val title: String,
    val id: Int,
    @SerialName("user_id")
    val userId: Int
)
