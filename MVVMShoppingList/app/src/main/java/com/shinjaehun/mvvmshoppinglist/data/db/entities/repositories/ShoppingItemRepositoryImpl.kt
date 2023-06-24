package com.shinjaehun.mvvmshoppinglist.data.db.entities.repositories

import com.shinjaehun.mvvmshoppinglist.data.db.ShoppingItemDao
import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.shinjaehun.mvvmshoppinglist.ui.SortOrder
import javax.inject.Inject

class ShoppingItemRepositoryImpl @Inject constructor(private val dao: ShoppingItemDao) {
    fun getAllItems() = dao.getAllItems()

    suspend fun upsert(shoppingItem: ShoppingItem) = dao.upsert(shoppingItem)
//    suspend fun update(shoppingItem: ShoppingItem) = dao.upsert(shoppingItem)
//    suspend fun insert(shoppingItem: ShoppingItem) = dao.upsert(shoppingItem)
    suspend fun delete(shoppingItem: ShoppingItem) = dao.delete(shoppingItem)
//    suspend fun insertItems(shoppingItems: List<ShoppingItem>) = dao.insertItems(shoppingItems)
//    suspend fun sort(sortOrder: SortOrder) = dao.sort(sortOrder)

}