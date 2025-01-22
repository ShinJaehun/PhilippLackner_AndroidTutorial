package com.shinjaehun.bookpedia.book.data.repository

import com.shinjaehun.bookpedia.book.data.mappers.toBook
import com.shinjaehun.bookpedia.book.data.network.RemoteBookDataSource
import com.shinjaehun.bookpedia.book.domain.Book
import com.shinjaehun.bookpedia.book.domain.BookRepository
import com.shinjaehun.bookpedia.core.domain.DataError
import com.shinjaehun.bookpedia.core.domain.Result
import com.shinjaehun.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }
}