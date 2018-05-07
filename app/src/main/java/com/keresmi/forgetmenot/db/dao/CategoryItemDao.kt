package com.keresmi.forgetmenot.db.dao

import android.arch.persistence.room.*
import com.keresmi.forgetmenot.db.CategoryItem
import com.keresmi.forgetmenot.db.Item
import io.reactivex.Single


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
@Dao
interface CategoryItemDao {

    @Query("SELECT * FROM Items INNER JOIN Categories_items ON Items.name=Categories_items.itemName " +
            "WHERE Categories_items.categoryName=:name")
    fun getItemsByCategoryName(name: String): Single<List<Item>>

    @Query("SELECT * FROM Items INNER JOIN Categories_items ON Items.name=Categories_items.itemName " +
            "WHERE Categories_items.categoryName=:categoryName AND Categories_items.itemName=:itemName")
    fun getItemByCategoryAndItemName(categoryName: String, itemName: String): Single<Item>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(categoryItem: CategoryItem)

    @Delete
    fun delete(categoryItem: CategoryItem)
}