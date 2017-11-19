package com.keresmi.forgetmenot.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */

@Entity(tableName = "categories")
data class Category(@PrimaryKey @ColumnInfo(name = "categoryId") val categoryId: String,
                    @ColumnInfo(name = "categoryName") val categoryName: String)

@Entity(tableName = "items")
data class Item(@PrimaryKey @ColumnInfo(name = "itemId") val itemId: String,
                @ColumnInfo(name = "itemName") val itemName: String)