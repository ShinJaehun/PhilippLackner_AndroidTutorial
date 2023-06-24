package com.shinjaehun.mvvmshoppinglist.data.db

import android.content.Context
import com.shinjaehun.mvvmshoppinglist.data.db.entities.repositories.ShoppingItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ShoppingDatabaseModule {
    @Provides
    fun provideShoppingItemDao(shoppingDatabase: ShoppingDatabase): ShoppingItemDao {
        return shoppingDatabase.shoppingItemDao
    }

    @Provides
    fun provideShoppingDatabase(@ApplicationContext context: Context): ShoppingDatabase {
        return ShoppingDatabase(context)
    }

    @Provides
    fun provideRepository(dao: ShoppingItemDao): ShoppingItemRepositoryImpl {
        return ShoppingItemRepositoryImpl(dao)
    }
}