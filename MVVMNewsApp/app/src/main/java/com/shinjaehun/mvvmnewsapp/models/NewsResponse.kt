package com.shinjaehun.mvvmnewsapp.models

import com.shinjaehun.mvvmnewsapp.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)