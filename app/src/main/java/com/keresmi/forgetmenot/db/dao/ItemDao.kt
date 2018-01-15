package com.keresmi.forgetmenot.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.keresmi.forgetmenot.db.Item
import io.reactivex.Single


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
@Dao
interface ItemDao {

    @Query("SELECT * FROM Items")
    fun getAll(): Single<List<Item>>

    @Query("SELECT * FROM Items WHERE name LIKE :arg0 LIMIT 1")
    fun getByName(name: String): Single<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Item)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(items: List<Item>)
}