package com.shinjaehun.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double
)
// Coin도 @Serializable을 사용할 수 있는데
// 그렇게 하면 뭔가 아키텍처 구성 위반이래...