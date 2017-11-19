package com.keresmi.forgetmenot.categories

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class CategoriesFragment : Fragment(), CategoriesContract.View {

    private val presenter: CategoriesContract.Presenter = CategoriesPresenter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.setTitle(R.string.categories)
        presenter.attachView(this)
        presenter.getCategories()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showCategories(categories: List<CategoryVM>) {
        categories_recycler_view.layoutManager = GridLayoutManager(context, 2)
        categories_recycler_view.adapter = CategoriesAdapter(categories, context,
                { categoryVM -> openCategory(categoryVM.name) })
    }

    private fun openCategory(name: String) {
        Log.d("CategoriesFragment", "Item $name clicked")
    }
}