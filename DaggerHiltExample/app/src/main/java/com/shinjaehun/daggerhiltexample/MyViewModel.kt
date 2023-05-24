package com.shinjaehun.daggerhiltexample

import androidx.lifecycle.ViewModel
import com.shinjaehun.daggerhiltexample.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: dagger.Lazy<MyRepository>
): ViewModel() {
    init {
        repository.get()
    }
}