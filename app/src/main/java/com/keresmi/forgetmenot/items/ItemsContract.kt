package com.keresmi.forgetmenot.items

import com.keresmi.forgetmenot.db.dao.CategoryItemDao
import com.keresmi.forgetmenot.db.dao.ItemDao


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
interface ItemsContract {

    interface View {
        fun showItems(items: MutableList<ItemVM>)
        fun updateItem(item: ItemVM)
        fun removeItem(item: ItemVM)
        fun showMessage(messageRes: Int)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun init(itemDao: ItemDao, categoryItemDao: CategoryItemDao)
        fun getItems(categoryName: String)
        fun getItemImageNameList(): ArrayList<String>
        fun addItem(itemVM: ItemVM, categoryName: String)
        fun deleteItem(itemVM: ItemVM, categoryName: String)
        fun scheduleNotification(categoryName: String, timeInMillis: Long)
    }
}