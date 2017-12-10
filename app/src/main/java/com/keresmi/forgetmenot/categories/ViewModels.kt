package com.keresmi.forgetmenot.categories

import com.keresmi.forgetmenot.db.Category


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
data class CategoryVM(val name: String, val imageRes: Int) {
    constructor(category: Category) : this(category.name, category.imageRes)
}