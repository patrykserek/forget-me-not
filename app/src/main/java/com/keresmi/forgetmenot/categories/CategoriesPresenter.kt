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

    private fun mockCategories(): List<CategoryVM> = listOf(CategoryVM("Daily", "coffee"),
            CategoryVM("Work", "blazer"),
            CategoryVM("Study", "student"))
}