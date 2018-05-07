package com.keresmi.forgetmenot.items

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class AddItemAdapter constructor(private val context: Context,
                                 private val itemImageNameList: ArrayList<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val view = ImageView(context)
        view.setImageResource(getImageId(itemImageNameList[position]))
        view.scaleType = ImageView.ScaleType.CENTER_CROP
        (container as ViewPager).addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) =
            (container as ViewPager).removeView(`object` as View)

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

    override fun getCount(): Int = itemImageNameList.size

    fun getItem(position: Int) = itemImageNameList[position]

    private fun getImageId(imageName: String) = context.resources.getIdentifier(imageName,
            "drawable", context.packageName)
}