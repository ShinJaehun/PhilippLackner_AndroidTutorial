package com.shinjaehun.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.shinjaehun.bookpedia.book.data.database.DatabaseFactory
import com.shinjaehun.bookpedia.book.data.database.FavoriteBookDatabase
import com.shinjaehun.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.shinjaehun.bookpedia.book.data.network.RemoteBookDataSource
import com.shinjaehun.bookpedia.book.domain.BookRepository
import com.shinjaehun.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.shinjaehun.bookpedia.book.data.repository.DefaultBookRepository
import com.shinjaehun.bookpedia.book.presentation.SelectedBookViewModel
import com.shinjaehun.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.shinjaehun.bookpedia.book.presentation.book_list.BookListViewModel
import org.koin.core.module.Module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}