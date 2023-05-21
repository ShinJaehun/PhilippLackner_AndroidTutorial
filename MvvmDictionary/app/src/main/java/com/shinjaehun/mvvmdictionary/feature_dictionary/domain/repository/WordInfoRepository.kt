package com.shinjaehun.mvvmdictionary.feature_dictionary.domain.repository

import com.shinjaehun.mvvmdictionary.core.util.Resource
import com.shinjaehun.mvvmdictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    // 여기 Flow<Resource<>> 이렇게 쓰는 이유를 모르겠음.
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}