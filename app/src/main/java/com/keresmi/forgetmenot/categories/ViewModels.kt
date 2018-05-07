package com.keresmi.forgetmenot.categories

import com.keresmi.forgetmenot.db.Category


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
data class CategoryVM(val name: String, val imageName: String) {
    constructor(category: Category) : this(category.name, category.imageName)
}