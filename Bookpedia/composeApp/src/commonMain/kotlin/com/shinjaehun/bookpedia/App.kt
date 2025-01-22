package com.shinjaehun.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.shinjaehun.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.shinjaehun.bookpedia.book.data.repository.DefaultBookRepository
import com.shinjaehun.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.shinjaehun.bookpedia.book.presentation.book_list.BookListViewModel
import com.shinjaehun.bookpedia.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel(
            bookRepository = DefaultBookRepository(
                remoteBookDataSource = KtorRemoteBookDataSource(
                    httpClient = HttpClientFactory.create(engine)
                )
            )
        ) },
        onBookClick = {

        }
    )
}