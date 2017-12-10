package com.keresmi.forgetmenot.categories

import android.util.Log
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.db.Category
import com.keresmi.forgetmenot.db.dao.CategoryDao
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class CategoriesPresenter : CategoriesContract.Presenter {

    private val TAG = CategoriesPresenter::class.java.simpleName
    private var view: CategoriesContract.View? = null
    private var categoryDao: CategoryDao? = null

    override fun attachView(view: CategoriesContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun init(categoryDao: CategoryDao, listener: () -> Unit) {
        this.categoryDao = categoryDao
        savePredefinedCategories(listener)
    }

    override fun getCategories() {
        categoryDao?.getAllCategories()
                ?.map(this::convert)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ view?.showCategories(it) },
                        { error -> Log.e(TAG, "getCategories: Error: " + error.message) })
    }

    override fun addCategory(categoryVM: CategoryVM) {

    }

    private fun savePredefinedCategories(listener: () -> Unit) {
        Completable.fromCallable { categoryDao?.insertAll(getPredefinedCategories()) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener() },
                        { error -> Log.e(TAG, "Init categories error: " + error.message) })
    }

    private fun getPredefinedCategories(): List<Category> = listOf(Category("Daily", R.drawable.coffee),
            Category("Work", R.drawable.blazer), Category("Study", R.drawable.student))

    private fun getAddButton() = CategoryVM("", R.drawable.ic_add_white_48px)

    private fun convert(categoryList: List<Category>): List<CategoryVM> =
            categoryList.map(::CategoryVM)
                    .toMutableList()
                    .apply { add(getAddButton()) }
                    .toList()
}