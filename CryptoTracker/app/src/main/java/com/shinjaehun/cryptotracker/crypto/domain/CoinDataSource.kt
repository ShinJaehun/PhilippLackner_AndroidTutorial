package com.shinjaehun.cryptotracker.crypto.domain

import com.shinjaehun.cryptotracker.core.domain.util.NetworkError
import com.shinjaehun.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}