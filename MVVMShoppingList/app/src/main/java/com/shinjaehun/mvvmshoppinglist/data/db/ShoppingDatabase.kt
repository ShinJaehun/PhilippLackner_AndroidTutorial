package com.shinjaehun.mvvmshoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {
//    abstract fun getShoppingDao(): ShoppingDao
    abstract val shoppingItemDao: ShoppingItemDao
    companion object {
        private val DATABASE_NAME = "ShoppingDB.db"

        @Volatile // ShoppingDatabase가 둘 이상 있으면 안되기 때문에 volatile로 선언, volatile의 의미? 캐싱된 데이터를 읽어오지 않음?
        private var instance: ShoppingDatabase? = null

        // Any(), this의 관계...
//        private val LOCK = Any()

//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also {
//                instance = it
//            }
//        }

        operator fun invoke(context: Context): ShoppingDatabase =
            instance ?: synchronized(this) {
                createDatabase(context).also {
                    instance = it
                }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}