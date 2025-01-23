@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.shinjaehun.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}