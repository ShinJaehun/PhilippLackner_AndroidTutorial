package com.shinjaehun.cleanarchitecturenoteapp.feature_note.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
