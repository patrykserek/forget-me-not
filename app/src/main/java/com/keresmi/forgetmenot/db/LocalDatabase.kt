package com.keresmi.forgetmenot.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.keresmi.forgetmenot.db.dao.CategoryDao
import com.keresmi.forgetmenot.db.dao.CategoryItemDao
import com.keresmi.forgetmenot.db.dao.ItemDao

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
@Database(entities = arrayOf(Category::class, Item::class, CategoryItem::class), version = 4, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao
    abstract fun categoryItemDao(): CategoryItemDao

    companion object {

        @Volatile private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        LocalDatabase::class.java, "forget-me-not.db")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}