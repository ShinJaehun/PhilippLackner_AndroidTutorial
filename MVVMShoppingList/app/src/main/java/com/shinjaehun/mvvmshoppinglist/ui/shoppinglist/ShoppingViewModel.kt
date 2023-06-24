package com.shinjaehun.mvvmshoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.shinjaehun.mvvmshoppinglist.data.db.entities.repositories.ShoppingItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingItemRepositoryImpl
) : ViewModel() {

    // 걍 DB에 있는 정보를 가지고 오거나 집어 넣기만 하면 되므로 메모리에 항상 유지할 필요가 없는거 아냐?
//    val shoppingItems: MutableLiveData<List<ShoppingItem>> = MutableLiveData()

// 굳이 지금 당장 필요한 건 아니지 않나?
//    private val sortOrderFlow: MutableStateFlow<SortOrder> = MutableStateFlow(SortOrder.NONE)
//    private val allFlow: Flow<List<ShoppingItem>> = repository.getAllItems()
//    fun upsert(item: ShoppingItem) = GlobalScope.launch {
//        repository.upsert(item)
//    }
//    fun delete(item: ShoppingItem) =GlobalScope.launch {
//        repository.delete(item)
//    }
//    fun getAllShoppingItems() = repository.getAllShoppingItems()

    fun upsertItem(shoppingItem: ShoppingItem) {
        // GlobalScope.launch()와 뭐가 다를까?
        viewModelScope.launch {
            repository.upsert(shoppingItem)
        }
    }

//    fun updateItem(shoppingItem: ShoppingItem) {
//        viewModelScope.launch {
//            repository.update(shoppingItem)
//        }
//    }

    fun deleteItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            repository.delete(shoppingItem)
        }
    }

//    fun addItems(shoppingItems: List<ShoppingItem>) {
//        viewModelScope.launch {
//            repository.insertItems(shoppingItems)
//        }
//    }

    fun getAllShoppingItems() = repository.getAllItems()


}