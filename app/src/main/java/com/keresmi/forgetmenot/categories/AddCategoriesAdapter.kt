package com.keresmi.forgetmenot.categories

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class AddCategoriesAdapter constructor(private val context: Context,
                                       private val categoriesImagesResList: ArrayList<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val view = ImageView(context)
        view.setImageResource(categoriesImagesResList[position])
        view.scaleType = ImageView.ScaleType.CENTER_CROP
        (container as ViewPager).addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) =
            (container as ViewPager).removeView(`object` as View)

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

    override fun getCount(): Int = categoriesImagesResList.size

    fun getItem(position: Int) = categoriesImagesResList[position]
}