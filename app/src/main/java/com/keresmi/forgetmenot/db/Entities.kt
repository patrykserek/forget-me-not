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
data class Category(@PrimaryKey var name: String = "", var imageRes: Int = 0)

@Entity(tableName = "Items")
data class Item(@PrimaryKey var name: String = "", var imageRes: Int = 0)

@Entity(tableName = "Categories_items")
data class CategoryItem(var categoryName: String = "", var itemName: String = "",
                        @PrimaryKey(autoGenerate = true) var id: Long = 0L)

data class CategoryWithItems(@Embedded var category: Category,
                             @Relation(parentColumn = "name", entityColumn = "categoryName",
                                     entity = CategoryItem::class, projection = arrayOf("itemName"))
                             var itemNameList: List<String>)