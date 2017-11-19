package com.keresmi.forgetmenot.categories

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.ViewUtils.inflate
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class CategoriesAdapter(private val categories: List<CategoryVM>, private val context: Context,
                        private val listener: (CategoryVM) -> Unit) :
        RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = parent.inflate(R.layout.item_category)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) = holder.bind(categories[position], listener)

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryVm: CategoryVM, listener: (CategoryVM) -> Unit) {
            itemView.setOnClickListener { listener(categoryVm) }
            itemView.category_name.text = categoryVm.name
            val resId = getImageResByName(categoryVm.imageName)
            if (resId != 0) itemView.category_image.setImageResource(resId)
        }

        private fun getImageResByName(resName: String): Int =
                context.resources.getIdentifier(resName, "drawable", context.packageName)
    }
}