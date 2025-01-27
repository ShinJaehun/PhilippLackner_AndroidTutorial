package com.shinjaehun.cryptocurrencyapp.presentation.coin_detail

import com.shinjaehun.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String? = null
)
