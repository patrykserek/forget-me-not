package com.keresmi.forgetmenot.categories

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.utils.ClickListener
import com.keresmi.forgetmenot.utils.Extensions.inflate
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class CategoriesAdapter(private val context: Context, private val categories: MutableList<CategoryVM>,
                        private val listener: ClickListener<CategoryVM>) :
        RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    fun update(categoryVm: CategoryVM) {
        categories.add(categories.size - 1, categoryVm)
        notifyItemInserted(categories.size - 2)
        notifyItemChanged(categories.size - 1)
    }

    fun remove(categoryVm: CategoryVM) {
        val index = categories.indexOf(categoryVm)
        categories.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = parent.inflate(R.layout.item_category)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) = holder.bind(categories[position], listener)

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryVm: CategoryVM, listener: ClickListener<CategoryVM>) {
            if (categoryVm.name.isEmpty()) {
                itemView.category_name_background.visibility = View.GONE
                itemView.category_name.text = ""
            } else {
                itemView.category_name_background.visibility = View.VISIBLE
                itemView.category_name.text = categoryVm.name
            }
            itemView.setOnClickListener { listener.onClick(categoryVm) }
            itemView.setOnLongClickListener { listener.onLongClick(categoryVm); false }
            itemView.category_image.setImageResource(getImageId(categoryVm.imageName))
        }

        private fun getImageId(imageName: String) = context.resources.getIdentifier(imageName,
                "drawable", context.packageName)
    }
}