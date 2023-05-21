package com.shinjaehun.mvvmdictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shinjaehun.mvvmdictionary.feature_dictionary.domain.model.Meaning
import com.shinjaehun.mvvmdictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val meanings: List<Meaning>, // 원래는 데이터베이스에 각 Meaning을 하나씩 저장해야 하는데 귀찮으니까...
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            word = word,
            phonetic = phonetic
        )
    }
}
