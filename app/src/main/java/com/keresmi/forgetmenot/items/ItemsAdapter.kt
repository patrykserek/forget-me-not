package com.keresmi.forgetmenot.items

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.common.BaseRecyclerAdapter
import com.keresmi.forgetmenot.common.BaseViewHolder
import com.keresmi.forgetmenot.utils.ClickListener
import com.keresmi.forgetmenot.utils.Extensions.inflate
import kotlinx.android.synthetic.main.item_item.view.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class ItemsAdapter constructor(override val context: Context, override val items: MutableList<ItemVM>,
                               override val listener: ClickListener<ItemVM>) :
        BaseRecyclerAdapter<ItemVM, ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(parent.inflate(R.layout.item_item))

    inner class ItemViewHolder(itemView: View) : BaseViewHolder<ItemVM>(itemView) {
        override fun bind(item: ItemVM, listener: ClickListener<ItemVM>) {
            if (item.name.isNotEmpty()) {
                itemView.item_name.text = item.name
                itemView.item_image.setImageResource(getImageId(item.imageName))
            }
            if (item.name == "Add item")
                itemView.setOnClickListener { listener.onClick(item) }
            else
                itemView.setOnLongClickListener { listener.onLongClick(item); false }
        }
    }
}