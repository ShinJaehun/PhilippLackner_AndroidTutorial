package com.shinjaehun.ktorchatapp.presentation.chat

import com.shinjaehun.ktorchatapp.domain.model.Message

data class ChatState(
    val messages : List<Message> = emptyList(),
    val isLoading : Boolean = false,
)