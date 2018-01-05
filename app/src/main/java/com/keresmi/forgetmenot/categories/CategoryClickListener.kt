package com.keresmi.forgetmenot.categories


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
interface CategoryClickListener {
    fun onClick(categoryVM: CategoryVM)
    fun onLongClick(categoryVM: CategoryVM)
}