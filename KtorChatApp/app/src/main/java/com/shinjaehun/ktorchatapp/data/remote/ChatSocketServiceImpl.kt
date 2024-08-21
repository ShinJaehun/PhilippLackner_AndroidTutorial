package com.shinjaehun.ktorchatapp.data.remote

import android.util.Log
import com.shinjaehun.ktorchatapp.data.remote.dto.MessageDto
import com.shinjaehun.ktorchatapp.domain.model.Message
import com.shinjaehun.ktorchatapp.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import java.util.Locale.filter

private const val TAG = "ChatSocketServiceImpl"

class ChatSocketServiceImpl(
    private val client: HttpClient
): ChatSocketService {

    private var socket: WebSocketSession? = null

    override suspend fun initSession(username: String): Resource<Unit> {
        return try {
            socket = client.webSocketSession {
                url("${ChatSocketService.Endpoints.ChatSocket.url}?username=$username")
            }
            if (socket?.isActive == true) {
                Resource.Success(Unit)
            } else Resource.Error("couldn't establish a connection.")

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "${e.message}")
            Resource.Error(e.localizedMessage ?: "unknown error")
        }
    }
    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(
                Frame.Text(message)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<Message> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter {
                    it is Frame.Text
                }
                ?.map {
                    val json = (it as? Frame.Text)?.readText() ?: ""
                    val messageDto = Json.decodeFromString<MessageDto>(json)
                    messageDto.toMessage()
                } ?: flow { }

        } catch (e: Exception) {
            e.printStackTrace()
            flow{ }
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }

}