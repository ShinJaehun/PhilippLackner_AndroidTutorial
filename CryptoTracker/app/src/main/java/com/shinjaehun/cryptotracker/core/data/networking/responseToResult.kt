package com.shinjaehun.cryptotracker.core.data.networking

import com.shinjaehun.cryptotracker.core.domain.util.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.statement.HttpResponse
import com.shinjaehun.cryptotracker.core.domain.util.Result
import io.ktor.client.call.body

suspend inline fun <reified T> responseToResult(
    // reified 요거만 뭔지 모르겠음...
    response: HttpResponse
): Result<T, NetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}