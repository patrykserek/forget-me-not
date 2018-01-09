package com.keresmi.forgetmenot.items

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.utils.Extensions.toast
import kotlinx.android.synthetic.main.fragment_dialog_add_item.*


class AddItemDialog : DialogFragment() {

    var onSaveButtonClickedListener: ((ItemVM) -> Unit)? = null

    companion object {
        const val ITEMS_IMAGES = "ITEMS_IMAGES"
        fun newInstance(itemsImagesResList: ArrayList<Int>): AddItemDialog =
                AddItemDialog().apply {
                    arguments = Bundle().apply {
                        putIntegerArrayList(ITEMS_IMAGES, itemsImagesResList)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_dialog_add_item, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initListeners()
    }

    private fun initListeners() {
        item_fragment_dialog_button_cancel.setOnClickListener { dismiss() }
        onSaveButtonClickedListener?.let {
            item_fragment_dialog_button_save.setOnClickListener {
                if (isItemValid()) {
                    it(ItemVM(getItemName(), getItemImageRes()))
                    dismiss()
                } else {
                    getString(R.string.empty_item_name).toast(activity)
                }
            }
        }
    }

    private fun initViewPager() {
        item_fragment_dialog_pager_container.viewPager!!.adapter = AddItemAdapter(activity, getItemsImageResFromArgs())
        item_fragment_dialog_pager_container.viewPager!!.offscreenPageLimit = getItemsImageResFromArgs().size
        item_fragment_dialog_pager_container.viewPager!!.pageMargin = 15
        item_fragment_dialog_pager_container.viewPager!!.clipChildren = false
    }

    private fun isItemValid(): Boolean = !fragment_dialog_item_name.text.isEmpty()

    private fun getItemsImageResFromArgs() = arguments.getIntegerArrayList(ITEMS_IMAGES)

    private fun getItemName() = fragment_dialog_item_name.text.toString().trim().toLowerCase()

    private fun getItemImageRes(): Int {
        val position = item_fragment_dialog_pager_container.viewPager!!.currentItem
        return (item_fragment_dialog_pager_container.viewPager!!.adapter as AddItemAdapter).getItem(position)
    }
}


