package com.keresmi.forgetmenot.items

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class AddItemAdapter constructor(private val context: Context,
                                 private val itemsImagesResList: ArrayList<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val view = ImageView(context)
        view.setImageResource(itemsImagesResList[position])
        view.scaleType = ImageView.ScaleType.CENTER_CROP
        (container as ViewPager).addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) =
            (container as ViewPager).removeView(`object` as View)

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

    override fun getCount(): Int = itemsImagesResList.size

    fun getItem(position: Int) = itemsImagesResList[position]
}