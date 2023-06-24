package com.shinjaehun.mvvmshoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.shinjaehun.mvvmshoppinglist.ui.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllItems(): LiveData<List<ShoppingItem>>
//    fun getAllItems(): Flow<List<ShoppingItem>> // 이거 왜 LiveData에서 Flow로 바뀐 건데...


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(shoppingItem: ShoppingItem) // 근데 얜 왜 item 그대로???

    // 얘네는 나중에 구현하자!

//    @Insert
//    suspend fun insertItems(shoppingItems: List<ShoppingItem>)

//    suspend fun sort(sortOrder: SortOrder) = when(sortOrder) {
//        SortOrder.QUANTITY_ASC -> sortByQuantityAsc()
//        SortOrder.QUANTITY_DESC -> sortByQuantityDesc()
//        else -> {}
//    }
//
//    @Query("SELECT * FROM shopping_items ORDER BY amount ASC") // noOfItems는 뭔가???
//    abstract fun sortByQuantityAsc(): LiveData<List<ShoppingItem>>
//    @Query("SELECT * FROM shopping_items ORDER BY amount DESC")
//    abstract fun sortByQuantityDesc(): LiveData<List<ShoppingItem>>
}
