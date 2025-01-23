package com.shinjaehun.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.shinjaehun.bookpedia.app.App
import com.shinjaehun.bookpedia.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Bookpedia",
        ) {
            App()
        }
    }
}