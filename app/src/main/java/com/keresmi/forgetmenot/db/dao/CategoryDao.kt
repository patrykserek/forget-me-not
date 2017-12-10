package com.keresmi.forgetmenot.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
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

    @Query("SELECT * FROM Categories WHERE name LIKE :arg0 LIMIT 1")
    fun getCategoryByName(name: String): Single<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(categories: List<Category>)
}