package com.shinjaehun.mvvmdictionary.feature_dictionary.data.util

import java.lang.reflect.Type

interface JsonParser {
    // 뭐 어쨌든 JsonParser를 딴데서 가져오는 대신에 이렇게 쓸 수 있다는 거냐?
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String
}