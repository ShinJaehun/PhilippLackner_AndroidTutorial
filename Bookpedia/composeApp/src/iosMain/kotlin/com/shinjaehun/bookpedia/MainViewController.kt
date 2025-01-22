package com.shinjaehun.bookpedia

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.shinjaehun.bookpedia.di.initKoin
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }