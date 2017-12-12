package com.keresmi.forgetmenot.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
object ViewUtils {

    fun ViewGroup.inflate(layoutRes: Int): View =
            LayoutInflater.from(context).inflate(layoutRes, this, false)
}