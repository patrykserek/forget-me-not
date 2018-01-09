package com.keresmi.forgetmenot.categories

import android.util.Log
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.db.Category
import com.keresmi.forgetmenot.db.dao.CategoryDao
import io.reactivex.Completable
import io.reactivex.Single
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
        categoryDao?.getAll()
                ?.map(this::convert)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ view?.showCategories(it) },
                        { error -> Log.e(TAG, "getCategories: Error: " + error.message) })
    }

    override fun getCategoryImageResList(): ArrayList<Int> = arrayListOf(R.drawable.blazer,
            R.drawable.business, R.drawable.cable_car, R.drawable.christmas, R.drawable.coffee,
            R.drawable.easter, R.drawable.luggage, R.drawable.office, R.drawable.ship,
            R.drawable.shopping, R.drawable.sport, R.drawable.student, R.drawable.swimmers,
            R.drawable.touring, R.drawable.traffic, R.drawable.trolley)

    override fun addCategory(categoryVM: CategoryVM) {
        Single.fromCallable { categoryDao?.insert(Category(categoryVM.name, categoryVM.imageRes)) }
                .flatMap {
                    categoryDao?.getByName(categoryVM.name)
                            ?.map { category -> CategoryVM(category) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.updateCategory(it) },
                        { error ->
                            Log.e(TAG, "Add category error: " + error.message)
                            view?.showMessage(R.string.category_exists)
                        })
    }

    override fun deleteCategory(categoryVM: CategoryVM) {
        Single.fromCallable { categoryDao?.delete(Category(categoryVM.name, categoryVM.imageRes)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.removeCategory(categoryVM) },
                        { error ->
                            Log.e(TAG, "Delete category error: " + error.message)
                            view?.showMessage(R.string.deleting_category_failed)
                        })
    }

    private fun savePredefinedCategories(listener: () -> Unit) {
        Completable.fromCallable { categoryDao?.insertAll(getPredefinedCategories()) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener() },
                        { error -> Log.e(TAG, "Init categories error: " + error.message) })
    }

    private fun getPredefinedCategories(): List<Category> = listOf(Category("daily", R.drawable.coffee),
            Category("work", R.drawable.blazer), Category("study", R.drawable.student))

    private fun getAddButton() = CategoryVM("", R.drawable.ic_add_white_48px)

    private fun convert(categoryList: List<Category>): MutableList<CategoryVM> =
            categoryList.map(::CategoryVM)
                    .toMutableList()
                    .apply { add(getAddButton()) }
}