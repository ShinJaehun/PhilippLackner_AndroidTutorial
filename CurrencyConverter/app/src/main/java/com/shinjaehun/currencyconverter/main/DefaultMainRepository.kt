package com.shinjaehun.currencyconverter.main

import com.shinjaehun.currencyconverter.data.CurrencyApi
import com.shinjaehun.currencyconverter.data.models.CurrencyResponse
import com.shinjaehun.currencyconverter.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {

//    override suspend fun getRates(key: String, base: String): Resource<CurrencyResponse> {

    override suspend fun getRates(key: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(key)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}