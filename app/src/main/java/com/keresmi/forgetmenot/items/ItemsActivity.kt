package com.keresmi.forgetmenot.items

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.db.LocalDatabase
import com.keresmi.forgetmenot.utils.ClickListener
import com.keresmi.forgetmenot.utils.Extensions.toast
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.content_items.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class ItemsActivity : AppCompatActivity(), ItemsContract.View, ClickListener<ItemVM> {

    private val presenter: ItemsContract.Presenter = ItemsPresenter()

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
        fun newIntent(context: Context, categoryName: String): Intent =
                Intent(context, ItemsActivity::class.java).apply {
                    putExtra(CATEGORY_NAME, categoryName)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setSupportActionBar(toolbar)
        initListeners()
        title = getCategoryNameFromArgs()
        val db = LocalDatabase.getInstance(this)
        presenter.attachView(this)
        presenter.init(db.itemDao(), db.categoryItemDao())
        presenter.getItems(getCategoryNameFromArgs())
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun showItems(items: MutableList<ItemVM>) {
        items_recycler_view.layoutManager = GridLayoutManager(this, 3)
        items_recycler_view.adapter = ItemsAdapter(items, this)
    }

    override fun updateItem(item: ItemVM) {
        (items_recycler_view.adapter as ItemsAdapter).update(item)
    }

    override fun removeItem(item: ItemVM) {
        (items_recycler_view.adapter as ItemsAdapter).remove(item)
    }

    override fun showMessage(messageRes: Int) {
        getString(messageRes).toast(this)
    }

    override fun onClick(item: ItemVM) {
        onAddButtonClicked()
    }

    override fun onLongClick(item: ItemVM) {
        onItemLongClick(item)
    }

    private fun getCategoryNameFromArgs() = intent.getStringExtra(CATEGORY_NAME)

    private fun onAddButtonClicked() {
        val dialog = AddItemDialog.newInstance(presenter.getItemImageResList())
        dialog.onSaveButtonClickedListener = { itemVM -> presenter.addItem(itemVM,getCategoryNameFromArgs())}
        val fm = this@ItemsActivity.fragmentManager
        dialog.show(fm,"item_dialog")

    }

    private fun initListeners() {
        items_button_categories.setOnClickListener { onBackPressed(); finish() }
        items_button_alarm.setOnClickListener { showMessage(R.string.feature_not_implemented) }
    }

    private fun onItemLongClick(item: ItemVM) {
        if (item.name.isNotEmpty()) {
            showAlertDialog(item)
        }
    }

    private fun showAlertDialog(item: ItemVM) {
        val dialog = AlertDialog.Builder(this)
                .setTitle(getString(R.string.delete_item_title))
                .setMessage(getString(R.string.delete_item_alert))
                .setPositiveButton(R.string.delete, { _, _ -> presenter.deleteItem(item, getCategoryNameFromArgs()) })
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
