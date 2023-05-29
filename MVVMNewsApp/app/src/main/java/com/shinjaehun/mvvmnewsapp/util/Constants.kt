package com.shinjaehun.mvvmnewsapp.util

import com.shinjaehun.mvvmnewsapp.BuildConfig


class Constants {
    companion object {
        const val API_KEY = BuildConfig.NAPI_KEY
        const val BASE_URL = "https://newsapi.org"
        const val SEARCH_NEWS_TIME_DELAY = 500L
    }
}