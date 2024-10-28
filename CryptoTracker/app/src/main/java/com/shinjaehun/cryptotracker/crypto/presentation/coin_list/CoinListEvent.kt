package com.shinjaehun.cryptotracker.crypto.presentation.coin_list

import com.shinjaehun.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}