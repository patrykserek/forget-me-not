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
        fun showMessage(message: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun init(categoryDao: CategoryDao, listener: () -> Unit)
        fun getCategories()
        fun getCategoryImageResList(): ArrayList<Int>
        fun addCategory(categoryVM: CategoryVM)
    }
}