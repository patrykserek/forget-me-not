package com.keresmi.forgetmenot.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */

@Entity(tableName = "Categories")
data class Category(@PrimaryKey var name: String = "", var imageName: String = "")

@Entity(tableName = "Items")
data class Item(@PrimaryKey var name: String = "", var imageName: String = "")

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
