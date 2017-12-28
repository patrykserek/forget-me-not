package com.keresmi.forgetmenot.items

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.utils.Extensions.inflate
import kotlinx.android.synthetic.main.item_item.view.*


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class ItemsAdapter constructor(private val items: MutableList<ItemVM>,
                               private val onAddClickedListener: () -> Unit) :
        RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    fun update(itemVm: ItemVM) {
        items.add(items.size - 1, itemVm)
        notifyItemInserted(items.size - 2)
        notifyItemChanged(items.size - 1)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
            holder.bind(items[position], onAddClickedListener)

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(parent.inflate(R.layout.item_item))

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemVM: ItemVM, onAddClickedListener: () -> Unit) {
            if (itemVM.name.isNotEmpty()) {
                itemView.item_name.text = itemVM.name
                itemView.item_image.setImageResource(itemVM.imageRes)
            }
            if (itemVM.name == "Add item") itemView.setOnClickListener { onAddClickedListener() }
        }
    }
}