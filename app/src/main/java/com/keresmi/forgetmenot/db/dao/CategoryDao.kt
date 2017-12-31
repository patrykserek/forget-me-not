package com.keresmi.forgetmenot.db.dao

import android.arch.persistence.room.*
import com.keresmi.forgetmenot.db.Category
import io.reactivex.Single

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
@Dao
interface CategoryDao {

    @Query("SELECT * FROM Categories")
    fun getAll(): Single<List<Category>>

    @Query("SELECT * FROM Categories WHERE name LIKE :arg0 LIMIT 1")
    fun getByName(name: String): Single<Category>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(category: Category)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(categories: List<Category>)

    @Delete
    fun delete(category: Category)
}