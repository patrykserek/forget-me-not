package com.keresmi.forgetmenot.categories

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.db.LocalDatabase
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class CategoriesFragment : Fragment(), CategoriesContract.View {

    private val presenter: CategoriesContract.Presenter = CategoriesPresenter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.setTitle(R.string.categories)
        presenter.attachView(this)
        presenter.init(LocalDatabase.getInstance(context).categoryDao(), { presenter.getCategories() })
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showCategories(categories: MutableList<CategoryVM>) {
        categories_recycler_view.layoutManager = GridLayoutManager(context, 2)
        categories_recycler_view.adapter = CategoriesAdapter(categories,
                { categoryVM -> onCategoryClicked(categoryVM.name) })
    }

    override fun updateCategory(categoryVM: CategoryVM) {
        (categories_recycler_view.adapter as CategoriesAdapter).update(categoryVM)
    }

    private fun onCategoryClicked(name: String) {
        if (name.isEmpty()) {
            val dialog = AddCategoryDialog.newInstance(presenter.getCategoryImageResList())
            dialog.onSaveButtonClickedListener = { categoryVM -> presenter.addCategory(categoryVM) }
            dialog.show(activity.fragmentManager, AddCategoryDialog::class.java.simpleName)
        }
    }
}