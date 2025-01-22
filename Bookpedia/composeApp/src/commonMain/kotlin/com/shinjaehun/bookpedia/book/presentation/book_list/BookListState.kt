package com.shinjaehun.bookpedia.book.presentation.book_list

import com.shinjaehun.bookpedia.book.domain.Book
import com.shinjaehun.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
//    val searchResults: List<Book> = sample_books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

//val sample_books = (1..100).map {
//    Book(
//        id = it.toString(),
//        title = "Book $it",
//        imageUrl = "https://test.com",
//        authors = listOf("Philipp Lackner"),
//        description = "Description $it",
//        languages = emptyList(),
//        firstPublishYear = null,
//        averageRating = 4.67854,
//        ratingCount = 5,
//        numPages = 100,
//        numEditions = 3
//    )
//}