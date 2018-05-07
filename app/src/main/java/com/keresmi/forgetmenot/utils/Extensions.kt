package com.keresmi.forgetmenot.utils

import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
object Extensions {

    fun ViewGroup.inflate(layoutRes: Int): View =
            LayoutInflater.from(context).inflate(layoutRes, this, false)

    fun Any.toast(context: Context) =
        Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()

    inline fun <T: DialogFragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
            this.apply { arguments = Bundle().apply(argsBuilder) }
}