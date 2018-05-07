package com.keresmi.forgetmenot.common

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.keresmi.forgetmenot.utils.ClickListener

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>> :
        RecyclerView.Adapter<VH>() {

    abstract val context: Context
    abstract val items: MutableList<T>
    abstract val listener: ClickListener<T>

    fun update(item: T) {
        items.add(items.size - 1, item)
        notifyItemInserted(items.size - 2)
        notifyItemChanged(items.size - 1)
    }

    fun remove(item: T) {
        val index = items.indexOf(item)
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    protected fun getImageId(imageName: String) = context.resources.getIdentifier(imageName,
            "drawable", context.packageName)
}