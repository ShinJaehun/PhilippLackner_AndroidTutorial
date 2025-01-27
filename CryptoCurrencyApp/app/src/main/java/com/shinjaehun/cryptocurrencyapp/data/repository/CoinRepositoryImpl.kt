package com.shinjaehun.cryptocurrencyapp.data.repository

import com.shinjaehun.cryptocurrencyapp.common.Resource
import com.shinjaehun.cryptocurrencyapp.data.mapper.toCoin
import com.shinjaehun.cryptocurrencyapp.data.mapper.toCoinDetail
import com.shinjaehun.cryptocurrencyapp.data.remote.CoinPaprikaApi
//import com.shinjaehun.cryptocurrencyapp.data.remote.dto.CoinDto
//import com.shinjaehun.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.shinjaehun.cryptocurrencyapp.domain.model.Coin
import com.shinjaehun.cryptocurrencyapp.domain.model.CoinDetail
import com.shinjaehun.cryptocurrencyapp.domain.repository.CoinRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): Resource<List<Coin>> {
        return try {
            Resource.Success(
                data = api.getCoins().map { it.toCoin() }
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "HTTP Exception")
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "IO Exception")
        }
    }

    override suspend fun getCoinById(coinId: String): Resource<CoinDetail> {
        return try {
            Resource.Success(
                data = api.getCoinById(coinId).toCoinDetail()
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "HTTP Exception")
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "IO Exception")
        }
    }
}