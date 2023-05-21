package com.shinjaehun.mvvmdictionary.feature_dictionary.presentation

import com.shinjaehun.mvvmdictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
