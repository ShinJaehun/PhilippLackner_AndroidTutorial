package com.shinjaehun.plugins

import com.shinjaehun.room.RoomController
import com.shinjaehun.routes.chatSocket
import com.shinjaehun.routes.getAllMessages
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    install(Routing) {
        chatSocket(roomController)
        getAllMessages(roomController)
    }
}