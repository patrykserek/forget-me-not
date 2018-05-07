package com.keresmi.forgetmenot.items

import com.keresmi.forgetmenot.db.Item


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
data class ItemVM(val name: String, val imageName: String) {
    constructor(item: Item) : this(item.name, item.imageName)
}