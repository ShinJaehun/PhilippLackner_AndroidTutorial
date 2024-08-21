package com.shinjaehun.ktorchatapp.data.remote

import android.util.Log
import com.shinjaehun.ktorchatapp.data.remote.dto.MessageDto
import com.shinjaehun.ktorchatapp.domain.model.Message
import com.shinjaehun.ktorchatapp.util.Resource
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

private const val TAG = "MessageServiceImpl"

class MessageServiceImpl(
    private val client: HttpClient
) : MessageService {


    override suspend fun getAllMessages(): List<Message> {
        return try {
//            // 뭔가 바뀐 듯...
//            client.get<List<MessageDto>>(MessageService.Endpoints.GetAllMessages.url)
//            .map {
//                it.toMessage()
//            }
            val response: List<MessageDto> =
                client.get(urlString = MessageService.Endpoints.GetAllMessages.url).body()
            response.map {
                it.toMessage()
            }
        } catch (e: Exception) {
            Log.e(TAG, "error in MessageServiceImpl: ${e.message.toString()}")
            // error: Handshake exception, expected status code 101 but was 200 여기서 발생함
            emptyList()
        }
    }
}
