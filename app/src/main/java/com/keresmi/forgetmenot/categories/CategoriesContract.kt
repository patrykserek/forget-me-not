package com.keresmi.forgetmenot.categories


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
interface CategoriesContract {

    interface View {
        fun showCategories(categories: List<CategoryVM>)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun getCategories()
    }
}