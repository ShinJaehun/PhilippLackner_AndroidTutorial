package com.shinjaehun.ktorclientandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val email: String,
    val password: String
)