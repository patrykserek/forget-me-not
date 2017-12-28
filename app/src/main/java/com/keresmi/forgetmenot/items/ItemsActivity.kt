package com.keresmi.forgetmenot.items

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.db.LocalDatabase
import com.keresmi.forgetmenot.utils.Extensions.toast
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.content_items.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class ItemsActivity : AppCompatActivity(), ItemsContract.View {

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
        items_recycler_view.adapter = ItemsAdapter(items, { onAddButtonClicked() })
    }

    override fun updateItem(item: ItemVM) {
        (items_recycler_view.adapter as ItemsAdapter).update(item)
    }

    override fun showMessage(message: String) {
        message.toast(this)
    }

    private fun getCategoryNameFromArgs() = intent.getStringExtra(CATEGORY_NAME)

    private fun onAddButtonClicked() {
        showMessage("Feature not implemented yet")
        //show dialog to add new item here
    }

    private fun initListeners() {
        items_button_categories.setOnClickListener { onBackPressed(); finish() }
        items_button_alarm.setOnClickListener { showMessage("Feature not implemented yet") }
    }
}
