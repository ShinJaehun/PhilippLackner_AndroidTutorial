package com.shinjaehun.mvvmshoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shinjaehun.mvvmshoppinglist.data.db.entities.repositories.ShoppingItemRepositoryImpl


class ShoppingViewModelFactory(
    private val repository: ShoppingItemRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}