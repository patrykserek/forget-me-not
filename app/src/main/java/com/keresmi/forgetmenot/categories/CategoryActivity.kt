package com.keresmi.forgetmenot.categories

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.db.LocalDatabase
import com.keresmi.forgetmenot.items.ItemsActivity
import com.keresmi.forgetmenot.utils.ClickListener
import com.keresmi.forgetmenot.utils.Extensions.toast
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.content_categories.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class CategoryActivity : AppCompatActivity(), CategoriesContract.View, ClickListener<CategoryVM> {

    private val presenter: CategoriesContract.Presenter = CategoriesPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setSupportActionBar(toolbar)

        title = getString(R.string.categories)

        presenter.attachView(this)
        presenter.init(LocalDatabase.getInstance(this).categoryDao(), { presenter.getCategories() })
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showCategories(categories: MutableList<CategoryVM>) {
        categories_recycler_view.layoutManager = GridLayoutManager(this, 2)
        categories_recycler_view.adapter = CategoriesAdapter(this, categories, this)
    }

    override fun updateCategory(categoryVM: CategoryVM) {
        (categories_recycler_view.adapter as CategoriesAdapter).update(categoryVM)
    }

    override fun removeCategory(categoryVM: CategoryVM) {
        (categories_recycler_view.adapter as CategoriesAdapter).remove(categoryVM)
    }

    override fun showMessage(messageRes: Int) {
        getString(messageRes).toast(this)
    }

    override fun onClick(item: CategoryVM) {
        onCategoryClick(item.name)
    }

    override fun onLongClick(item: CategoryVM) {
        onCategoryLongClick(item)
    }

    private fun onCategoryClick(name: String) {
        if (name.isEmpty()) {
            val dialog = AddCategoryDialog.newInstance(presenter.getCategoryImageNameList())
            dialog.onSaveButtonClickedListener = { categoryVM -> presenter.addCategory(categoryVM) }
            dialog.show(fragmentManager, AddCategoryDialog::class.java.simpleName)
        } else {
            startActivity(ItemsActivity.newIntent(this, name))
        }
    }

    private fun onCategoryLongClick(categoryVM: CategoryVM) {
        if (categoryVM.name.isNotEmpty()) {
            showAlertDialog(categoryVM)
        }
    }

    private fun showAlertDialog(categoryVM: CategoryVM) {
        val dialog = AlertDialog.Builder(this)
                .setTitle(getString(R.string.delete_category_title))
                .setMessage(getString(R.string.delete_category_alert))
                .setPositiveButton(R.string.delete, { _, _ -> presenter.deleteCategory(categoryVM) })
                .setNegativeButton(R.string.cancel, { dialog, _ -> dialog.dismiss() })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.text))
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.text))
        }

        dialog.show()
    }
}
