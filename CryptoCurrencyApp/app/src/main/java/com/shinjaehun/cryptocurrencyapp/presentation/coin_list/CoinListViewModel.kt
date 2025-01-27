package com.shinjaehun.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinjaehun.cryptocurrencyapp.common.Resource
import com.shinjaehun.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

//    private val _state = mutableStateOf(CoinListState())
//    val state: State<CoinListState> = _state

    var state by mutableStateOf(CoinListState())
        private set

    init {
        getCoins() // 이렇게 하면 side effect가 발생한다고 이렇게 하지 말라고 해신디...
    }

    private fun getCoins() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            repository.getCoins().let {result ->
                when(result){
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            coins = result.data ?: emptyList(),
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            coins = emptyList(),
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