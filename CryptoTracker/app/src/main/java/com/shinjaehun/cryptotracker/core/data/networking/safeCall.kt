package com.shinjaehun.cryptotracker.core.data.networking

import com.shinjaehun.cryptotracker.core.domain.util.NetworkError
import com.shinjaehun.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive() // 이걸 왜 하는지 모르겠음!
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}