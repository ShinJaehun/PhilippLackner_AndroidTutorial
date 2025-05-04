package com.shinjaehun.jetpackcomposepagingdemo

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}