package com.shinjaehun.cryptocurrencyapp.domain.repository

//import com.shinjaehun.cryptocurrencyapp.data.remote.dto.CoinDetailDto
//import com.shinjaehun.cryptocurrencyapp.data.remote.dto.CoinDto
import com.shinjaehun.cryptocurrencyapp.common.Resource
import com.shinjaehun.cryptocurrencyapp.domain.model.Coin
import com.shinjaehun.cryptocurrencyapp.domain.model.CoinDetail

interface CoinRepository {

//    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoins(): Resource<List<Coin>>

//    suspend fun getCoinById(coinId: String): CoinDetailDto
    suspend fun getCoinById(coinId: String): Resource<CoinDetail>
}