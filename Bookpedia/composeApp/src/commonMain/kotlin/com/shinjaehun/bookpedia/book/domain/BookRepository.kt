package com.shinjaehun.bookpedia.book.domain

import com.shinjaehun.bookpedia.core.domain.DataError
import com.shinjaehun.bookpedia.core.domain.EmptyResult
import com.shinjaehun.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    // repository를 Data 레이어에 집어 넣게 되면 repositories interface에서 domain 레이어의 entities를 import해야 한다…
    // 원래대로 repositories interface가 domain 레이어에 있으면 entities를 import할 필요가 없음…
    // 도메인 계층은 가장 안쪽에 위치하고 있다.
    // 위에서 말했듯이 의존성은 안쪽으로만 존재하기 때문에 제일 안쪽에 있는 도메인 계층은 다른 계층에 의존하지 않고 완전히 격리된다.
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String)
}