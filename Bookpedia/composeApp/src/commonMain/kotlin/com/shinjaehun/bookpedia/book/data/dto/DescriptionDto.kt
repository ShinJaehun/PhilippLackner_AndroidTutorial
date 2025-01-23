package com.shinjaehun.bookpedia.book.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionDto(
    // 얘는 BookWorkDtoSerializer에서 value를 얻기 위해 필요...
    val value: String
)
