package com.keresmi.forgetmenot.db

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */

@Entity(tableName = "Categories")
data class Category(@PrimaryKey(autoGenerate = true) val id: Long, val name: String, val imageRes: Int)

@Entity(tableName = "Items")
data class Item(@PrimaryKey(autoGenerate = true) val id: Long, val name: String, val imageRes: Int)

@Entity(tableName = "Categories_items")
data class CategoryItem(@PrimaryKey(autoGenerate = true) val id: Long, val categoryId: Long, val itemId: Long)

data class CategoryWithItems(@Embedded val category: Category,
                             @Relation(parentColumn = "id", entityColumn = "categoryId",
                                     entity = CategoryItem::class, projection = arrayOf("itemId"))
                             val itemIdList: List<Long>)