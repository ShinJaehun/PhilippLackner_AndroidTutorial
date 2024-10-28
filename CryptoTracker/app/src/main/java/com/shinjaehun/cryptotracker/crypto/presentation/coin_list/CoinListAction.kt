package com.shinjaehun.cryptotracker.crypto.presentation.coin_list

import com.shinjaehun.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
//    data object OnRefresh: CoinListAction // 사실 필요 없으니까...
}