package com.shinjaehun.bookpedia.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shinjaehun.bookpedia.book.presentation.SelectedBookViewModel
import com.shinjaehun.bookpedia.book.presentation.book_detail.BookDetailAction
import com.shinjaehun.bookpedia.book.presentation.book_detail.BookDetailScreenRoot
import com.shinjaehun.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.shinjaehun.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.shinjaehun.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

//@Composable
//@Preview
//fun App(engine: HttpClientEngine) {
//    BookListScreenRoot(
//        viewModel = remember { BookListViewModel(
//            bookRepository = DefaultBookRepository(
//                remoteBookDataSource = KtorRemoteBookDataSource(
//                    httpClient = HttpClientFactory.create(engine)
//                )
//            )
//        ) },
//        onBookClick = {
//
//        }
//    )
//}

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.BookGraph
    ) {
        navigation<Route.BookGraph>(
            startDestination = Route.BookList
        ) {
            composable<Route.BookList>(
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() }
            ) {
                val viewModel = koinViewModel<BookListViewModel>()
                val selectedBookViewModel =
                    it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                LaunchedEffect(true) {
                    selectedBookViewModel.onSelectBook(null)
                }

                BookListScreenRoot(
                    viewModel = viewModel,
                    onBookClick = { book ->
                        // 그러니까 결과적으로는 selectedBookViewModel에 book도 집어 넣고
                        // navigate할 때 id도 넣어서 호출하는 셈
                        selectedBookViewModel.onSelectBook(book)
                        navController.navigate(
                            Route.BookDetail(book.id)
                        )
                    }
                )
            }
            composable<Route.BookDetail>(
                enterTransition = { slideInHorizontally { initialOffset ->
                    initialOffset
                } },
                exitTransition = { slideOutHorizontally { initialOffset ->
                    initialOffset
                } }
            ) {entry ->
                val args = entry.toRoute<Route.BookDetail>()
                val selectedBookViewModel =
                    entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                val viewModel = koinViewModel<BookDetailViewModel>()
                val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column {
//                        Text("Book Detail Screen. The id is ${args.id}")
//                        Text("Book Detail Screen. The book is ${selectedBook}")
//                    }
//                }

                LaunchedEffect(selectedBook) {
                    selectedBook?.let {
                        viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                    }
                }

                BookDetailScreenRoot(
                    viewModel = viewModel,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    // 정확히 맞는 건지는 모르겠는데
    // navController를 이용해서 parentEntry(Route.BookGraph)의 viewModel을 koin에게 요청
    // 이때 viewModel type T는 SelectedBookViewModel이다...
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}