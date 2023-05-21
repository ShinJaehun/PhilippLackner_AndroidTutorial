package com.shinjaehun.mvvmdictionary.feature_dictionary.domain.model

import com.shinjaehun.mvvmdictionary.feature_dictionary.data.remote.dto.DefinitionDto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)