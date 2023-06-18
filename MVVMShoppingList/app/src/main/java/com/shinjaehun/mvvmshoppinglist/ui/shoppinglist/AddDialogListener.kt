package com.shinjaehun.mvvmshoppinglist.ui.shoppinglist

import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}