package com.shinjaehun.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shinjaehun.mvvmnewsapp.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long // 아마 coroutine 비동기로 데이터를 insert?

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>> // 얘는 걍 바로 조회하는 거니까 suspend 아님?

    @Delete
    suspend fun deleteArticle(article: Article)
}