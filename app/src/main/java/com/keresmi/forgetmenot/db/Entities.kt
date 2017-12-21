package com.keresmi.forgetmenot.db

import android.arch.persistence.room.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */

@Entity(tableName = "Categories")
data class Category(@PrimaryKey var name: String = "", var imageRes: Int = 0)

@Entity(tableName = "Items")
data class Item(@PrimaryKey var name: String = "", var imageRes: Int = 0)

@Entity(tableName = "Categories_items", primaryKeys = arrayOf("categoryName", "itemName"),
        indices = arrayOf(Index("itemName")), foreignKeys = arrayOf(
        ForeignKey(entity = Category::class,
                parentColumns = arrayOf("name"),
                childColumns = arrayOf("categoryName"),
                onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Item::class,
                parentColumns = arrayOf("name"),
                childColumns = arrayOf("itemName"),
                onDelete = ForeignKey.CASCADE)))
data class CategoryItem(var categoryName: String = "", var itemName: String = "")
