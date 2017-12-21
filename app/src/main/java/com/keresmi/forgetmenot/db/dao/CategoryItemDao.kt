package com.keresmi.forgetmenot.db.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.keresmi.forgetmenot.db.CategoryItem
import com.keresmi.forgetmenot.db.Item
import io.reactivex.Single


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
interface CategoryItemDao {

    @Query("SELECT * FROM Items INNER JOIN Categories_items ON Items.name=Categories_items.itemName " +
            "WHERE Categories_items.categoryName=:arg0")
    fun getItemsByCategoryName(name: String): Single<List<Item>>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(categoryItem: CategoryItem)
}