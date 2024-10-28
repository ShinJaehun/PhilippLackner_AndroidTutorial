package com.shinjaehun.cryptotracker.core.data.networking

import com.shinjaehun.cryptotracker.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1) // /assets
        else -> BuildConfig.BASE_URL + url // /assets
    }
}