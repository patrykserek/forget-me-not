package com.keresmi.forgetmenot.categories

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.utils.Extensions.toast
import com.keresmi.forgetmenot.utils.Extensions.withArgs
import kotlinx.android.synthetic.main.fragment_dialog_add_category.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class AddCategoryDialog : DialogFragment() {

    var onSaveButtonClickedListener: ((CategoryVM) -> Unit)? = null

    companion object {
        const val CATEGORIES_IMAGES = "CATEGORIES_IMAGES"
        fun newInstance(categoryImageNameList: ArrayList<String>): AddCategoryDialog =
                AddCategoryDialog().withArgs {
                    putStringArrayList(CATEGORIES_IMAGES, categoryImageNameList)
                }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_dialog_add_category, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initListeners()
    }

    private fun initListeners() {
        fragment_dialog_button_cancel.setOnClickListener { dismiss() }
        onSaveButtonClickedListener?.let {
            fragment_dialog_button_save.setOnClickListener {
                if (isCategoryValid()) {
                    it(CategoryVM(getCategoryName(), getCategoryImageName()))
                    dismiss()
                } else {
                    getString(R.string.empty_category_name).toast(activity)
                }
            }
        }
    }

    private fun initViewPager() {
        fragment_dialog_pager_container.viewPager?.adapter = AddCategoriesAdapter(activity, getCategoriesImageNameFromArgs())
        fragment_dialog_pager_container.viewPager?.offscreenPageLimit = getCategoriesImageNameFromArgs().size
        fragment_dialog_pager_container.viewPager?.pageMargin = 15
        fragment_dialog_pager_container.viewPager?.clipChildren = false
    }

    private fun isCategoryValid(): Boolean = fragment_dialog_category_name.text.isNotEmpty()

    private fun getCategoriesImageNameFromArgs() = arguments.getStringArrayList(CATEGORIES_IMAGES)

    private fun getCategoryName() = fragment_dialog_category_name.text.toString().trim().toLowerCase()

    private fun getCategoryImageName(): String {
        val position = fragment_dialog_pager_container.viewPager!!.currentItem
        return (fragment_dialog_pager_container.viewPager!!.adapter as AddCategoriesAdapter).getItem(position)
    }
}