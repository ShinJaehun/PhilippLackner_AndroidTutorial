package com.shinjaehun.mvvmnewsapp.repository

import com.shinjaehun.mvvmnewsapp.api.RetrofitInstance
import com.shinjaehun.mvvmnewsapp.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}