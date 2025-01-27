package com.shinjaehun.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinjaehun.cryptocurrencyapp.common.Constants
import com.shinjaehun.cryptocurrencyapp.common.Resource
import com.shinjaehun.cryptocurrencyapp.domain.repository.CoinRepository
import com.shinjaehun.cryptocurrencyapp.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _state = mutableStateOf(CoinListState())
//    val state: State<CoinListState> = _state

    var state by mutableStateOf(CoinDetailState())
        private set

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            repository.getCoinById(coinId).let { result ->
                when(result){
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            coin = result.data,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            coin = null,
                            error = result.message
                        )
                    }
                    else -> {
                        state = state.copy(
                            isLoading = false,
                            error = "An unexpectedError occurred."
                        )
                    }
                }
            }
        }
    }
}