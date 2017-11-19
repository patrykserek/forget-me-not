package com.keresmi.forgetmenot.categories


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class CategoriesPresenter : CategoriesContract.Presenter {

    private var view: CategoriesContract.View? = null

    override fun attachView(view: CategoriesContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun getCategories() {
        view?.showCategories(mockCategories())
    }

    private fun mockCategories(): List<CategoryVM> = listOf(CategoryVM("Travel", "travel_category"),
            CategoryVM("Work", "work_category"),
            CategoryVM("Daily", "daily_category"))
}