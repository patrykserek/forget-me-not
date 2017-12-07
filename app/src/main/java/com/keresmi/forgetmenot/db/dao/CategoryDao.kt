package com.keresmi.forgetmenot.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.keresmi.forgetmenot.db.Category
import io.reactivex.Single


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
@Dao
interface CategoryDao {

    @Query("SELECT * FROM Categories")
    fun getAllCategories(): Single<List<Category>>

    @Query("SELECT * FROM Categories WHERE name LIKE :name LIMIT 1")
    fun getCategoryByName(name: String): Single<Category>

    @Insert()
    fun insert(category: Category)

    @Insert()
    fun insertAll(categories: List<Category>)
}