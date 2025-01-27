package com.shinjaehun.cryptocurrencyapp.data.mapper

import com.shinjaehun.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.shinjaehun.cryptocurrencyapp.data.remote.dto.CoinDto
import com.shinjaehun.cryptocurrencyapp.domain.model.Coin
import com.shinjaehun.cryptocurrencyapp.domain.model.CoinDetail
import com.shinjaehun.cryptocurrencyapp.domain.model.TeamMember

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team.map { TeamMember(
            id = it.id,
            name = it.name,
            position = it.position,
        ) }
    )
}