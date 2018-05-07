package com.keresmi.forgetmenot.common

import android.support.v7.widget.RecyclerView
import android.view.View
import com.keresmi.forgetmenot.utils.ClickListener


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
abstract class BaseViewHolder<T> constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, listener: ClickListener<T>)
}