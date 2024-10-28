package com.shinjaehun.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinjaehun.cryptotracker.core.domain.util.onError
import com.shinjaehun.cryptotracker.core.domain.util.onSuccess
import com.shinjaehun.cryptotracker.crypto.domain.CoinDataSource
import com.shinjaehun.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart {
            loadCoins() // 이게 좋다고 합니다...
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

//    val state = _state.asStateFlow()
//
//    init {
//        loadCoins() //side effect 위험이 있음...
//    }

    fun onAction(action: CoinListAction) {
        when(action){
            is CoinListAction.OnCoinClick -> {

            }
            // 사실 필요 없으니까...
//            CoinListAction.OnRefresh -> {
//                loadCoins()
//            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            coinDataSource
                .getCoins()
                .onSuccess { coins ->
                    _state.update { it.copy(
                        isLoading = false,
                        coins.map { it.toCoinUi() }
                    )}
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}