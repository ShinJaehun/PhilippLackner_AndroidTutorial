package com.shinjaehun.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.shinjaehun.bookpedia.app.App
import com.shinjaehun.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }