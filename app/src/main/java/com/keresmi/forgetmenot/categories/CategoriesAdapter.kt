package com.keresmi.forgetmenot.categories

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.common.BaseRecyclerAdapter
import com.keresmi.forgetmenot.common.BaseViewHolder
import com.keresmi.forgetmenot.utils.ClickListener
import com.keresmi.forgetmenot.utils.Extensions.inflate
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class CategoriesAdapter(override val context: Context, override val items: MutableList<CategoryVM>,
                        override val listener: ClickListener<CategoryVM>) :
        BaseRecyclerAdapter<CategoryVM, CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = parent.inflate(R.layout.item_category)
        return CategoryViewHolder(view)
    }

    inner class CategoryViewHolder(itemView: View) : BaseViewHolder<CategoryVM>(itemView) {
        override fun bind(item: CategoryVM, listener: ClickListener<CategoryVM>) {
            if (item.name.isEmpty()) {
                itemView.category_name_background.visibility = View.GONE
                itemView.category_name.text = ""
            } else {
                itemView.category_name_background.visibility = View.VISIBLE
                itemView.category_name.text = item.name
            }
            itemView.setOnClickListener { listener.onClick(item) }
            itemView.setOnLongClickListener { listener.onLongClick(item); false }
            itemView.category_image.setImageResource(getImageId(item.imageName))
        }
    }
}