package com.shinjaehun.currencyconverter.main

import com.shinjaehun.currencyconverter.data.models.CurrencyResponse
import com.shinjaehun.currencyconverter.util.Resource

interface MainRepository {
    // for test view
    suspend fun getRates(key: String): Resource<CurrencyResponse>
//    suspend fun getRates(key: String, base: String): Resource<CurrencyResponse>

}