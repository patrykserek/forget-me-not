package com.keresmi.forgetmenot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
object ViewUtils {

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}