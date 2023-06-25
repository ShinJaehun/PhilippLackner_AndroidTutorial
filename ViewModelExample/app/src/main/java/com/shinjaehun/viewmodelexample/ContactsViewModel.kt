package com.shinjaehun.viewmodelexample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ContactsViewModel(
    val helloWorld: String
): ViewModel() {
    // ViewModel() 이게 있어야 activity가 존재하는 한 viewmodel이 다시 생성되지 않음...

    var backgroundColor by mutableStateOf(Color.White)
        private set

    fun changeBackgroundColor() {
        backgroundColor = Color.Red
    }
}