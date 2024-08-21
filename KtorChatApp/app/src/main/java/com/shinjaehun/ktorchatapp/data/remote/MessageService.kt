package com.shinjaehun.ktorchatapp.data.remote

import com.shinjaehun.ktorchatapp.domain.model.Message
import com.shinjaehun.ktorchatapp.util.Resource

interface MessageService {
    suspend fun getAllMessages(): List<Message>

    companion object {
        const val BASE_URL = "ws://192.168.200.201:8081"
    }

    sealed class Endpoints(val url: String) {
        object GetAllMessages: Endpoints("$BASE_URL/messages")
    }
}