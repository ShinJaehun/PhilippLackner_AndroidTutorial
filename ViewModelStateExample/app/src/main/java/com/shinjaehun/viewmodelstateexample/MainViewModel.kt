package com.shinjaehun.viewmodelstateexample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

//    private val _color = MutableStateFlow(0xFFFFFFFF)
//    val color = _color.asStateFlow()

    // stateFlow를 사용하면 뭐 이런식으로 flow 연산자를 사용할 수 있다는 말!
//    val redValue = color.map {
//
//    }.stateIn()

//    var composeColor by mutableStateOf(0xFFFFFFFF)
//        private set

//    fun generateNewColor() {
//        val color = Random.nextLong(0xFFFFFFFF)
//        _color.value = color
//        composeColor = color
//    }

//    val color = savedStateHandle.getStateFlow("color", 0xFFFFFFFF) // SavedStateHandle: 그냥 이렇게 초기화시킬 수 있음!
//
//    var composeColor by mutableStateOf(
//        // SavedStateHandle을 state에 직접 적용하는 경우...
//        savedStateHandle.get<Long>("color") ?: 0xFFFFFFFF
//
//        // 어차피 savedStateHandle과 composeColor 양쪽 값을 변경해서 업데이트해야 함!
//        // savedStateHandle["color"] = color
//        // composeColor = color
//    )
//        private set
//
//    fun generateNewColor() {
//        val color = Random.nextLong(0xFFFFFFFF)
//        savedStateHandle["color"] = color
//        composeColor = color
//    }

    val color = savedStateHandle.getStateFlow("color", 0xFFFFFFFF) // 이렇게 하면 간단히 구현 가능하단 말이지...

    fun generateNewColor() {
        val color = Random.nextLong(0xFFFFFFFF)
        savedStateHandle["color"] = color
    }
}