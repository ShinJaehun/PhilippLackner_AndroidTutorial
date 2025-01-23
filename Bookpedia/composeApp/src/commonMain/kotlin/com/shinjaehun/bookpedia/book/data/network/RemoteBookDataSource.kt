package com.shinjaehun.bookpedia.book.data.network

import com.shinjaehun.bookpedia.book.data.dto.BookWorkDto
import com.shinjaehun.bookpedia.book.data.dto.SearchResponseDto
import com.shinjaehun.bookpedia.core.domain.DataError
import com.shinjaehun.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}