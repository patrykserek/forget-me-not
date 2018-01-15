package com.keresmi.forgetmenot.utils


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
interface ClickListener<in T> {
    fun onClick(item: T)
    fun onLongClick(item: T)
}