package com.shinjaehun.di

import com.shinjaehun.data.MessageDataSource
import com.shinjaehun.data.MessageDataSourceImpl
import com.shinjaehun.room.RoomController
import org.koin.dsl.module
import org.koin.dsl.single
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import kotlin.math.sin

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("message_db_shinjaehun")
    }

    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single {
        RoomController(get())
    }
}