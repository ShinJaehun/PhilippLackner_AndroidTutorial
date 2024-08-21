package com.shinjaehun.ktorchatapp.di

import com.shinjaehun.ktorchatapp.data.remote.ChatSocketService
import com.shinjaehun.ktorchatapp.data.remote.ChatSocketServiceImpl
import com.shinjaehun.ktorchatapp.data.remote.MessageService
import com.shinjaehun.ktorchatapp.data.remote.MessageServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
//            install(WebSockets) {
//                contentConverter = KotlinxWebsocketSerializationConverter(Json)
//            }
            install(WebSockets)

            install(ContentNegotiation) {
                json()
            }

//            install(ContentNegotiation) {
//                json()
//            }
//                register(
//                    ContentType.Text.Html, KotlinxSerializationConverter(
//                        Json {
//                            prettyPrint = true
//                            isLenient = true
//                            ignoreUnknownKeys = true
//                        }
//                    )
//                )
//                json()
//                addDefaultResponseValidation()
//                register(
//                    KotlinxSerializationConverter(
//                        Json()
//                    )
//                )
//                serializer = KotlinxSerializer()
//                json()
//                json(Json {
//                    prettyPrint = true
//                    isLenient = true
//                    ignoreUnknownKeys = true
//                })
        }
    }

    @Provides
    @Singleton
    fun provideMessageService(client: HttpClient): MessageService {
        return MessageServiceImpl(client)
    }


    @Provides
    @Singleton
    fun provideChatSocketService(client: HttpClient): ChatSocketService {
        return ChatSocketServiceImpl(client)
    }
}