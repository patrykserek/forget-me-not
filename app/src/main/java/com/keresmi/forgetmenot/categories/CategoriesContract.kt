package com.keresmi.forgetmenot.categories

import com.keresmi.forgetmenot.db.dao.CategoryDao


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
interface CategoriesContract {

    interface View {
        fun showCategories(categories: MutableList<CategoryVM>)
        fun updateCategory(categoryVM: CategoryVM)
        fun removeCategory(categoryVM: CategoryVM)
        fun showMessage(messageRes: Int)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun init(categoryDao: CategoryDao, listener: () -> Unit)
        fun getCategories()
        fun getCategoryImageNameList(): ArrayList<String>
        fun addCategory(categoryVM: CategoryVM)
        fun deleteCategory(categoryVM: CategoryVM)
    }
}